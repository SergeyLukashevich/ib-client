package lv.sergluka.tws;

import com.ib.client.*;
import lv.sergluka.tws.impl.*;
import lv.sergluka.tws.impl.future.TwsFuture;
import lv.sergluka.tws.impl.future.TwsListFuture;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.*;

public class TwsClient implements AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(TwsClient.class);

    private EClientSocket socket;
    private TwsReader reader;
    private TwsSender sender;
    private ConnectionMonitor connectionMonitor;
    private TwsBaseWrapper wrapper;

    enum Status {
        DISCONNECTED,
        CONNECTING,
        CONNECTED,
        CONNECTION_LOST,
        DISCONNECTING,
    }

    private Status status = Status.DISCONNECTED;

    @Override
    public void close() throws IOException {
        connectionMonitor.disconnect();
    }

    public void connect(final @NotNull String ip, final int port, final int connId) {
        log.debug("Connecting...");
        status = Status.CONNECTING;

        EJavaSignal signal = new EJavaSignal();
        sender = new TwsSender(this);
        wrapper = new TwsWrapper();
        socket = new EClientSocket(wrapper, signal);
        reader = new TwsReader(socket, signal);
        connectionMonitor = new ConnectionMonitor(socket, reader);

        TwsFuture fConnect = sender.post(TwsSender.Event.REQ_CONNECT,
                () -> connectionMonitor.connect(ip, port, connId));
        fConnect.get();
    }

    public void disconnect() throws TimeoutException {
        if (!isConnected()) {
            log.info("Already is disconnected");
            return;
        }

        log.debug("Disconnecting...");
        status = Status.DISCONNECTING;
        connectionMonitor.disconnect();
        status = Status.DISCONNECTED;
        log.info("Disconnected");
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected() && status == Status.CONNECTED;
    }

    public TwsFuture<Integer> reqId() {
        return sender.postIfConnected(TwsSender.Event.REQ_ID, () -> socket.reqIds(-1));
    }

    public Integer reqIdsSync() throws TimeoutException {
        try {
            return reqId().get(10, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            throw new TimeoutException("Timeout of 'reqId' call");
        }
    }

    public TwsFuture<Object> placeOrder(@NotNull Contract contract, @NotNull Order order) throws TimeoutException {
        Integer id = reqIdsSync();
        return sender.postIfConnected(TwsSender.Event.REQ_ORDER_PLACE, () -> socket.placeOrder(id, contract, order));
    }

    public TwsFuture<List<ContractDetails>> reqContractDetails(@NotNull Contract contract) {
        shouldBeConnected();

        final Integer id;
        try {
             id = reqIdsSync();
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        }

        return sender.postIfConnected(TwsSender.Event.REQ_CONTRACT_DETAIL,
                () -> socket.reqContractDetails(id, contract));
    }

    private void shouldBeConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected");
        }
    }

    private class TwsWrapper extends TwsBaseWrapper {

        TwsWrapper() {
            super(sender);
        }

        @Override
        public void connectAck() {
            log.info("Connection opened. version: {}", socket.serverVersion());
            status = Status.CONNECTED;

            reader.start();
            super.connectAck();
        }

        @Override
        public void connectionClosed() {
            log.error("TWS closes the impl");
            status = Status.CONNECTION_LOST;
            connectionMonitor.reconnect(1000);
        }

        @Override
        public void error(final Exception e) {
            if (e instanceof SocketException) {
                if (status == Status.DISCONNECTING || status == Status.DISCONNECTED) {
                    log.debug("Socket has been closed at shutdown");
                    return;
                }

                status = Status.CONNECTION_LOST;
                log.warn("Connection lost", e);
                connectionMonitor.reconnect(1000);
            }

            log.error("TWS error", e);
        }

        @Override
        public void error(final String str) {
            log.error("TWS error: ", str);
        }

        @Override
        public void error(final int id, final int errorCode, final String errorMsg) {
            if (id == -1 && (errorCode == 2104 || errorCode == 2106)) {
                log.debug("Connection is OK: {}", errorMsg);
                return;
            }

            log.error("Terminal returns an error: id={}, code={}, msg={}", id, errorCode, errorMsg);

            switch (errorCode) {
                case 503: // The TWS is out of date and must be upgraded
                    break;
                case 2104: // OK
                case 2106: // OK
                    log.debug("Connection is OK: {}", errorMsg);
                    break;
                default:
                    connectionMonitor.reconnect(10_000);
            }
        }
    }
}
