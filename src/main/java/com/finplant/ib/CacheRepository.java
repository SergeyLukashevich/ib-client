package com.finplant.ib;

import java.util.Collection;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.finplant.ib.types.IbMarketDepth;
import com.finplant.ib.types.IbOrder;
import com.finplant.ib.types.IbPortfolio;
import com.finplant.ib.types.IbPosition;
import com.finplant.ib.types.IbTick;
import com.ib.client.Contract;

@SuppressWarnings("unused")
public interface CacheRepository {
    Map<Integer, IbOrder> getOrders();

    Map<IbMarketDepth.Key, IbMarketDepth> getOrderBook(Contract contract);

    IbTick getTick(int tickerId);

    Collection<IbPortfolio> getPortfolio();

    Collection<IbPosition> getPositions();

    @Nullable
    IbPosition getPosition(String account, Contract contract);

    @Nullable
    IbPortfolio getPortfolio(Contract contract);

    void clear();
}