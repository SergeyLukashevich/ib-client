package lv.sergluka.tws.impl.cache;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.ib.client.Contract;
import lv.sergluka.tws.impl.types.TwsOrder;
import lv.sergluka.tws.impl.types.TwsOrderStatus;
import lv.sergluka.tws.impl.types.TwsPosition;
import lv.sergluka.tws.impl.types.TwsTick;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

// TODO: some entries can remain forever. We have to cleanup expired entries.
public class CacheRepository {

    private static final Logger log = LoggerFactory.getLogger(CacheRepository.class);

    private final ConcurrentHashMap<Integer, TwsOrder> orders = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<PositionKey, TwsPosition> positions = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, TwsTick> ticks = new ConcurrentHashMap<>();

    // AFter order placing, some statuses goes first, before `openOrder` callback, so storing then separately
    private final LinkedHashMap<Integer, Set<TwsOrderStatus>> statuses = new LinkedHashMap<>();

    public CacheRepository() {
    }

    public boolean addOrder(TwsOrder order) {
        final Set<TwsOrderStatus> set = statuses.remove(order.getOrderId());
        if (set != null) {
            order.addStatuses(set);
        }
        final AtomicReference<Boolean> result = new AtomicReference<>(false);
        orders.compute(order.getOrderId(), (key, value)-> {
            if (value != null) {
                log.debug("Order {} already has been added", order.getOrderId());
                result.set(false);
                return value;
            }

            result.set(true);
            return order;
        });

        return result.get();
    }

    public List<TwsOrder> getOrders() {
        return ImmutableList.copyOf(orders.values());
    }

    public boolean addNewStatus(@NotNull TwsOrderStatus status) {
        TwsOrder order = orders.get(status.getOrderId());
        if (order == null) {
            log.debug("Status update for not (yet?) existing order {}: {}", status.getOrderId(), status);

            final Set<TwsOrderStatus> set = statuses.computeIfAbsent(status.getOrderId(), (key) -> new HashSet<>());
            if (!set.add(status)) {
                log.debug("[{}]: Status '{}' already exists", status.getOrderId(), status.getStatus());
                return false;
            }

            return false;
        }

        return order.addStatus(status);
    }

    public void updatePosition(String account, Contract contract, double position, double avgCost) {
        positions.put(new PositionKey(account, contract.conid()), new TwsPosition(account, contract, position, avgCost));
    }

    public TwsTick updateTick(int tickerId, Consumer<TwsTick> consumer) {
        TwsTick tick = ticks.computeIfAbsent(tickerId, (key) -> new TwsTick()) ;
        consumer.accept(tick);
        return tick;
    }

    public TwsTick getTick(int tickerId) {
        return ticks.get(tickerId);
    }

    public ImmutableMap<PositionKey, TwsPosition> getPositions() {
        return ImmutableMap.copyOf(positions);
    }

    public TwsPosition getPosition(String account, Contract contract) {
        Objects.requireNonNull(account);
        Objects.requireNonNull(contract);
        if (contract.conid() == 0) {
            throw new IllegalArgumentException("Contract has a id 0");
        }
        return positions.get(new PositionKey(account, contract.conid()));
    }
}
