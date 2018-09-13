package com.finplant.ib.impl.types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.ib.client.TickAttr;

public class IbTickImpl implements IbTick {

    private static final Logger log = LoggerFactory.getLogger(IbTickImpl.class);

    public enum Types {
        BID_SIZE(0),
        BID(1),
        ASK(2),
        ASK_SIZE(3),
        LAST(4),
        LAST_SIZE(5),
        HIGH(6),
        LOW(7),
        VOLUME(8),
        CLOSE_PRICE(9),
        OPEN_TICK(14),
        LOW13_WEEKS(15),
        HIGH13_WEEKS(16),
        LOW26_WEEKS(17),
        HIGH26_WEEKS(18),
        LOW52_WEEKS(19),
        HIGH52_WEEKS(20),
        VOLUME_AVERAGE(21),
        OPTION_HISTORICAL_VOLATILITY(23),
        OPTION_IMPLIED_VOLATILITY(24),
        OPTION_CALL_OPEN_INTEREST(27),
        OPTION_PUT_OPEN_INTEREST(28),
        OPTION_CALL_VOLUME(29),
        OPTION_PUT_VOLUME(30),
        INDEX_FUTURE_PREMIUM(31),
        BID_EXCHANGE(32),
        ASK_EXCHANGE(33),
        ACTION_VOLUME(34),
        AUCTION_PRICE(35),
        ACTION_IMBALANCE(36),
        MARK_PRICE(37),
        LAST_TIMESTAMP(45),
        SHORTABLE(46),
        RT_VOLUME(48),
        HALTED(49),
        BID_YIELD(50),
        ASK_YIELD(51),
        LAST_YIELD(52),
        TRADE_COUNT(54),
        TRADE_RATE(55),
        VOLUME_RATE(56),
        LAST_RTHTRADE(57),
        RT_HISTORICAL_VOLATILITY(58),
        IB_DIVIDENDS(59),
        REGULATORY_IMBALANCE(61),
        NEWS(62),
        SHORT_TERM_VOLUME3_MIN(63),
        SHORT_TERM_VOLUME5_MIN(64),
        SHORT_TERM_VOLUME10_MIN(65),
        DELAYED_BID(66),
        DELAYED_ASK(67),
        DELAYED_LAST(68),
        DELAYED_BID_SIZE(69),
        DELAYED_ASK_SIZE(70),
        DELAYED_LAST_SIZE(71),
        DELAYED_HIGH_PRICE(72),
        DELAYED_LOW_PRICE(73),
        DELAYED_VOLUME(74),
        DELAYED_CLOSE(75),
        DELAYED_OPEN(76),
        RT_TRADE_VOLUME(77),
        CREDITMAN_MARK_PRICE(78),
        CREDITMAN_SLOW_MAR_KPRICE(79),
        DELAYED_BID_OPTION(80),
        DELAYED_ASK_OPTION(81),
        DELAYED_LAST_OPTION(82),
        DELAYED_MODEL_OPTION(83),
        LAST_EXCHANGE(84),
        LAST_REGULATORY_TIME(85),
        FUTURES_OPEN_INTEREST(86);

        private static Map<Integer, Types> map = new HashMap<>();

        static {
            for (Types type : Types.values()) {
                map.put(type.value, type);
            }
        }
        private Integer value;

        private Types(Integer value) {
            this.value = value;
        }

        public static Types valueOf(Integer type) {
            return map.get(type);
        }
        public Integer getValue() {
            return value;
        }

    }

    private Integer bidSize;
    private Integer askSize;
    private Integer lastSize;
    private Integer volume;
    private Integer volumeAverage;
    private Integer optionCallOpenInterest;
    private Integer optionPutOpenInterest;
    private Integer optionCallVolume;
    private Integer optionPutVolume;
    private Integer actionVolume;
    private Integer actionImbalance;
    private Integer regulatoryImbalance;
    private Integer shortTermVolume3Min;
    private Integer shortTermVolume5Min;
    private Integer shortTermVolume10Min;
    private Integer delayedBidSize;
    private Integer delayedAskSize;
    private Integer delayedLastSize;
    private Integer delayedVolume;
    private Integer futuresOpenInterest;

    private Double bid;
    private Double ask;
    private Double lastPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closePrice;
    private Double openTick;
    private Double low13Weeks;
    private Double high13Weeks;
    private Double low26Weeks;
    private Double high26Weeks;
    private Double low52Weeks;
    private Double high52Weeks;
    private Double auctionPrice;
    private Double markPrice;
    private Double bidYield;
    private Double askYield;
    private Double lastYield;
    private Double lastRthTrade;
    private Double delayedBid;
    private Double delayedAsk;
    private Double delayedLast;
    private Double delayedHighPrice;
    private Double delayedLowPrice;
    private Double delayedClose;
    private Double delayedOpen;
    private Double creditmanMarkPrice;
    private Double creditmanSlowMarkPrice;
    private Double delayedBidOption;
    private Double delayedAskOption;
    private Double delayedLastOption;
    private Double delayedModelOption;

    private String bidExchange;
    private String askExchange;
    private String lastTimestamp;
    private String rtVolume;
    private String ibDividends;
    private String news;
    private String rtTradeVolume;
    private String lastExchange;
    private String lastRegulatoryTime;

    private Double optionHistoricalVolatility;
    private Double optionImpliedVolatility;
    private Double indexFuturePremium;
    private Double shortable;
    private Double halted;
    private Double tradeCount;
    private Double tradeRate;
    private Double volumeRate;
    private Double rtHistoricalVolatility;

    private LocalDateTime updateTime;

    public IbTickImpl() {
    }

    public void setIntValue(Integer type, Integer value) {

        Types enumType = Types.valueOf(type);
        switch (enumType) {
            case BID_SIZE:
                bidSize = value;
                break;
            case ASK_SIZE:
                askSize = value;
                break;
            case LAST_SIZE:
                lastSize = value;
                break;
            case VOLUME:
                volume = value;
                break;
            case VOLUME_AVERAGE:
                volumeAverage = value;
                break;
            case OPTION_CALL_OPEN_INTEREST:
                optionCallOpenInterest = value;
                break;
            case OPTION_PUT_OPEN_INTEREST:
                optionPutOpenInterest = value;
                break;
            case OPTION_CALL_VOLUME:
                optionCallVolume = value;
                break;
            case OPTION_PUT_VOLUME:
                optionPutVolume = value;
                break;
            case ACTION_VOLUME:
                actionVolume = value;
                break;
            case ACTION_IMBALANCE:
                actionImbalance = value;
                break;
            case REGULATORY_IMBALANCE:
                regulatoryImbalance = value;
                break;
            case SHORT_TERM_VOLUME3_MIN:
                shortTermVolume3Min = value;
                break;
            case SHORT_TERM_VOLUME5_MIN:
                shortTermVolume5Min = value;
                break;
            case SHORT_TERM_VOLUME10_MIN:
                shortTermVolume10Min = value;
                break;
            case DELAYED_BID_SIZE:
                delayedBidSize = value;
                break;
            case DELAYED_ASK_SIZE:
                delayedAskSize = value;
                break;
            case DELAYED_LAST_SIZE:
                delayedLastSize = value;
                break;
            case DELAYED_VOLUME:
                delayedVolume = value;
                break;
            case FUTURES_OPEN_INTEREST:
                futuresOpenInterest = value;
                break;
            default:
                log.warn("Unknown int type for tick, type={}, value={}", type, value);
        }
        log.trace("Set value: {} = {}", enumType, value);
    }

    public void setPriceValue(Integer type, Double value, TickAttr attrib) {
        Types enumType = Types.valueOf(type);
        switch (enumType) {
            case BID:
                bid = value;
                break;
            case ASK:
                ask = value;
                break;
            case LAST:
                lastPrice = value;
                break;
            case HIGH:
                highPrice = value;
                break;
            case LOW:
                lowPrice = value;
                break;
            case CLOSE_PRICE:
                closePrice = value;
                break;
            case OPEN_TICK:
                openTick = value;
                break;
            case LOW13_WEEKS:
                low13Weeks = value;
                break;
            case HIGH13_WEEKS:
                high13Weeks = value;
                break;
            case LOW26_WEEKS:
                low26Weeks = value;
                break;
            case HIGH26_WEEKS:
                high26Weeks = value;
                break;
            case LOW52_WEEKS:
                low52Weeks = value;
                break;
            case HIGH52_WEEKS:
                high52Weeks = value;
                break;
            case AUCTION_PRICE:
                auctionPrice = value;
                break;
            case MARK_PRICE:
                markPrice = value;
                break;
            case BID_YIELD:
                bidYield = value;
                break;
            case ASK_YIELD:
                askYield = value;
                break;
            case LAST_YIELD:
                lastYield = value;
                break;
            case LAST_RTHTRADE:
                lastRthTrade = value;
                break;
            case DELAYED_BID:
                delayedBid = value;
                break;
            case DELAYED_ASK:
                delayedAsk = value;
                break;
            case DELAYED_LAST:
                delayedLast = value;
                break;
            case DELAYED_HIGH_PRICE:
                delayedHighPrice = value;
                break;
            case DELAYED_LOW_PRICE:
                delayedLowPrice = value;
                break;
            case DELAYED_CLOSE:
                delayedClose = value;
                break;
            case DELAYED_OPEN:
                delayedOpen = value;
                break;
            case CREDITMAN_MARK_PRICE:
                creditmanMarkPrice = value;
                break;
            case CREDITMAN_SLOW_MAR_KPRICE:
                creditmanSlowMarkPrice = value;
                break;
            case DELAYED_BID_OPTION:
                delayedBidOption = value;
                break;
            case DELAYED_ASK_OPTION:
                delayedAskOption = value;
                break;
            case DELAYED_LAST_OPTION:
                delayedLastOption = value;
                break;
            case DELAYED_MODEL_OPTION:
                delayedModelOption = value;
                break;
            default:
                log.warn("Unknown price type for tick, type={}, value={}", type, value);
        }
        log.trace("Set value: {} = {}, attr: [auto exec: {}, past limit: {}, pre open: {}]",
                  enumType, value, attrib.canAutoExecute(), attrib.pastLimit(), attrib.preOpen());
    }

    public void setStringValue(Integer type, String value) {
        Types enumType = Types.valueOf(type);
        switch (enumType) {
            case BID_EXCHANGE:
                bidExchange = value;
                break;
            case ASK_EXCHANGE:
                askExchange = value;
                break;
            case LAST_TIMESTAMP:
                lastTimestamp = value;
                break;
            case RT_VOLUME:
                rtVolume = value;
                break;
            case IB_DIVIDENDS:
                ibDividends = value;
                break;
            case NEWS:
                news = value;
                break;
            case RT_TRADE_VOLUME:
                rtTradeVolume = value;
                break;
            case LAST_EXCHANGE:
                lastExchange = value;
                break;
            case LAST_REGULATORY_TIME:
                lastRegulatoryTime = value;
                break;
            default:
                log.warn("Unknown string type for tick, type={}, value={}", type, value);
        }
        log.trace("Set value: {} = {}", enumType, value);
    }

    public void setGenericValue(Integer type, Double value) {
        Types enumType = Types.valueOf(type);
        switch (enumType) {
            case OPTION_HISTORICAL_VOLATILITY:
                optionHistoricalVolatility = value;
                break;
            case OPTION_IMPLIED_VOLATILITY:
                optionImpliedVolatility = value;
                break;
            case INDEX_FUTURE_PREMIUM:
                indexFuturePremium = value;
                break;
            case SHORTABLE:
                shortable = value;
                break;
            case HALTED:
                halted = value;
                break;
            case TRADE_COUNT:
                tradeCount = value;
                break;
            case TRADE_RATE:
                tradeRate = value;
                break;
            case VOLUME_RATE:
                volumeRate = value;
                break;
            case RT_HISTORICAL_VOLATILITY:
                rtHistoricalVolatility = value;
                break;
            default:
                log.warn("Unknown generic type for tick, type={}, value={}", type, value);
        }
        log.trace("Set value: {} = {}", enumType, value);
    }

    public void refreshUpdateTime() {
        updateTime = LocalDateTime.now();
    }

    @Override
    public Integer getBidSize() {
        return bidSize;
    }

    @Override
    public Integer getAskSize() {
        return askSize;
    }

    @Override
    public Integer getLastSize() {
        return lastSize;
    }

    @Override
    public Integer getVolume() {
        return volume;
    }

    @Override
    public Integer getVolumeAverage() {
        return volumeAverage;
    }

    @Override
    public Integer getOptionCallOpenInterest() {
        return optionCallOpenInterest;
    }

    @Override
    public Integer getOptionPutOpenInterest() {
        return optionPutOpenInterest;
    }

    @Override
    public Integer getOptionCallVolume() {
        return optionCallVolume;
    }

    @Override
    public Integer getOptionPutVolume() {
        return optionPutVolume;
    }

    @Override
    public Integer getActionVolume() {
        return actionVolume;
    }

    @Override
    public Integer getActionImbalance() {
        return actionImbalance;
    }

    @Override
    public Integer getRegulatoryImbalance() {
        return regulatoryImbalance;
    }

    @Override
    public Integer getShortTermVolume3Min() {
        return shortTermVolume3Min;
    }

    @Override
    public Integer getShortTermVolume5Min() {
        return shortTermVolume5Min;
    }

    @Override
    public Integer getShortTermVolume10Min() {
        return shortTermVolume10Min;
    }

    @Override
    public Integer getDelayedBidSize() {
        return delayedBidSize;
    }

    @Override
    public Integer getDelayedAskSize() {
        return delayedAskSize;
    }

    @Override
    public Integer getDelayedLastSize() {
        return delayedLastSize;
    }

    @Override
    public Integer getDelayedVolume() {
        return delayedVolume;
    }

    @Override
    public Integer getFuturesOpenInterest() {
        return futuresOpenInterest;
    }

    @Override
    public Double getBid() {
        return bid;
    }

    @Override
    public Double getAsk() {
        return ask;
    }

    @Override
    public Double getLastPrice() {
        return lastPrice;
    }

    @Override
    public Double getHighPrice() {
        return highPrice;
    }

    @Override
    public Double getLowPrice() {
        return lowPrice;
    }

    @Override
    public Double getClosePrice() {
        return closePrice;
    }

    @Override
    public Double getOpenTick() {
        return openTick;
    }

    @Override
    public Double getLow13Weeks() {
        return low13Weeks;
    }

    @Override
    public Double getHigh13Weeks() {
        return high13Weeks;
    }

    @Override
    public Double getLow26Weeks() {
        return low26Weeks;
    }

    @Override
    public Double getHigh26Weeks() {
        return high26Weeks;
    }

    @Override
    public Double getLow52Weeks() {
        return low52Weeks;
    }

    @Override
    public Double getHigh52Weeks() {
        return high52Weeks;
    }

    @Override
    public Double getAuctionPrice() {
        return auctionPrice;
    }

    @Override
    public Double getMarkPrice() {
        return markPrice;
    }

    @Override
    public Double getBidYield() {
        return bidYield;
    }

    @Override
    public Double getAskYield() {
        return askYield;
    }

    @Override
    public Double getLastYield() {
        return lastYield;
    }

    @Override
    public Double getLastRthTrade() {
        return lastRthTrade;
    }

    @Override
    public Double getDelayedBid() {
        return delayedBid;
    }

    @Override
    public Double getDelayedAsk() {
        return delayedAsk;
    }

    @Override
    public Double getDelayedLast() {
        return delayedLast;
    }

    @Override
    public Double getDelayedHighPrice() {
        return delayedHighPrice;
    }

    @Override
    public Double getDelayedLowPrice() {
        return delayedLowPrice;
    }

    @Override
    public Double getDelayedClose() {
        return delayedClose;
    }

    @Override
    public Double getDelayedOpen() {
        return delayedOpen;
    }

    @Override
    public Double getCreditmanMarkPrice() {
        return creditmanMarkPrice;
    }

    @Override
    public Double getCreditmanSlowMarkPrice() {
        return creditmanSlowMarkPrice;
    }

    @Override
    public Double getDelayedBidOption() {
        return delayedBidOption;
    }

    @Override
    public Double getDelayedAskOption() {
        return delayedAskOption;
    }

    @Override
    public Double getDelayedLastOption() {
        return delayedLastOption;
    }

    @Override
    public Double getDelayedModelOption() {
        return delayedModelOption;
    }

    @Override
    public String getBidExchange() {
        return bidExchange;
    }

    @Override
    public String getAskExchange() {
        return askExchange;
    }

    @Override
    public String getLastTimestamp() {
        return lastTimestamp;
    }

    @Override
    public String getRtVolume() {
        return rtVolume;
    }

    @Override
    public String getIbDividends() {
        return ibDividends;
    }

    @Override
    public String getNews() {
        return news;
    }

    @Override
    public String getRtTradeVolume() {
        return rtTradeVolume;
    }

    @Override
    public String getLastExchange() {
        return lastExchange;
    }

    @Override
    public String getLastRegulatoryTime() {
        return lastRegulatoryTime;
    }

    @Override
    public Double getOptionHistoricalVolatility() {
        return optionHistoricalVolatility;
    }

    @Override
    public Double getOptionImpliedVolatility() {
        return optionImpliedVolatility;
    }

    @Override
    public Double getIndexFuturePremium() {
        return indexFuturePremium;
    }

    @Override
    public Double getShortable() {
        return shortable;
    }

    @Override
    public Double getHalted() {
        return halted;
    }

    @Override
    public Double getTradeCount() {
        return tradeCount;
    }

    @Override
    public Double getTradeRate() {
        return tradeRate;
    }

    @Override
    public Double getVolumeRate() {
        return volumeRate;
    }

    @Override
    public Double getRtHistoricalVolatility() {
        return rtHistoricalVolatility;
    }

    @Override
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder("{");
        if (bidSize != null) {
            buffer.append("bidSize=").append(bidSize);
        }
        if (askSize != null) {
            buffer.append(", askSize=").append(askSize);
        }
        if (lastSize != null) {
            buffer.append(", lastSize=").append(lastSize);
        }
        if (volume != null) {
            buffer.append(", volume=").append(volume);
        }
        if (volumeAverage != null) {
            buffer.append(", volumeAverage=").append(volumeAverage);
        }
        if (optionCallOpenInterest != null) {
            buffer.append(", optionCallOpenInterest=").append(optionCallOpenInterest);
        }
        if (optionPutOpenInterest != null) {
            buffer.append(", optionPutOpenInterest=").append(optionPutOpenInterest);
        }
        if (optionCallVolume != null) {
            buffer.append(", optionCallVolume=").append(optionCallVolume);
        }
        if (optionPutVolume != null) {
            buffer.append(", optionPutVolume=").append(optionPutVolume);
        }
        if (actionVolume != null) {
            buffer.append(", actionVolume=").append(actionVolume);
        }
        if (actionImbalance != null) {
            buffer.append(", actionImbalance=").append(actionImbalance);
        }
        if (regulatoryImbalance != null) {
            buffer.append(", regulatoryImbalance=").append(regulatoryImbalance);
        }
        if (shortTermVolume3Min != null) {
            buffer.append(", shortTermVolume3Min=").append(shortTermVolume3Min);
        }
        if (shortTermVolume5Min != null) {
            buffer.append(", shortTermVolume5Min=").append(shortTermVolume5Min);
        }
        if (shortTermVolume10Min != null) {
            buffer.append(", shortTermVolume10Min=").append(shortTermVolume10Min);
        }
        if (delayedBidSize != null) {
            buffer.append(", delayedBidSize=").append(delayedBidSize);
        }
        if (delayedAskSize != null) {
            buffer.append(", delayedAskSize=").append(delayedAskSize);
        }
        if (delayedLastSize != null) {
            buffer.append(", delayedLastSize=").append(delayedLastSize);
        }
        if (delayedVolume != null) {
            buffer.append(", delayedVolume=").append(delayedVolume);
        }
        if (futuresOpenInterest != null) {
            buffer.append(", futuresOpenInterest=").append(futuresOpenInterest);
        }
        if (bid != null) {
            buffer.append(", bid=").append(bid);
        }
        if (ask != null) {
            buffer.append(", ask=").append(ask);
        }
        if (lastPrice != null) {
            buffer.append(", lastPrice=").append(lastPrice);
        }
        if (highPrice != null) {
            buffer.append(", highPrice=").append(highPrice);
        }
        if (lowPrice != null) {
            buffer.append(", lowPrice=").append(lowPrice);
        }
        if (closePrice != null) {
            buffer.append(", closePrice=").append(closePrice);
        }
        if (openTick != null) {
            buffer.append(", openTick=").append(openTick);
        }
        if (low13Weeks != null) {
            buffer.append(", low13Weeks=").append(low13Weeks);
        }
        if (high13Weeks != null) {
            buffer.append(", high13Weeks=").append(high13Weeks);
        }
        if (low26Weeks != null) {
            buffer.append(", low26Weeks=").append(low26Weeks);
        }
        if (high26Weeks != null) {
            buffer.append(", high26Weeks=").append(high26Weeks);
        }
        if (low52Weeks != null) {
            buffer.append(", low52Weeks=").append(low52Weeks);
        }
        if (high52Weeks != null) {
            buffer.append(", high52Weeks=").append(high52Weeks);
        }
        if (auctionPrice != null) {
            buffer.append(", auctionPrice=").append(auctionPrice);
        }
        if (markPrice != null) {
            buffer.append(", markPrice=").append(markPrice);
        }
        if (bidYield != null) {
            buffer.append(", bidYield=").append(bidYield);
        }
        if (askYield != null) {
            buffer.append(", askYield=").append(askYield);
        }
        if (lastYield != null) {
            buffer.append(", lastYield=").append(lastYield);
        }
        if (lastRthTrade != null) {
            buffer.append(", lastRthTrade=").append(lastRthTrade);
        }
        if (delayedBid != null) {
            buffer.append(", delayedBid=").append(delayedBid);
        }
        if (delayedAsk != null) {
            buffer.append(", delayedAsk=").append(delayedAsk);
        }
        if (delayedLast != null) {
            buffer.append(", delayedLast=").append(delayedLast);
        }
        if (delayedHighPrice != null) {
            buffer.append(", delayedHighPrice=").append(delayedHighPrice);
        }
        if (delayedLowPrice != null) {
            buffer.append(", delayedLowPrice=").append(delayedLowPrice);
        }
        if (delayedClose != null) {
            buffer.append(", delayedClose=").append(delayedClose);
        }
        if (delayedOpen != null) {
            buffer.append(", delayedOpen=").append(delayedOpen);
        }
        if (creditmanMarkPrice != null) {
            buffer.append(", creditmanMarkPrice=").append(creditmanMarkPrice);
        }
        if (creditmanSlowMarkPrice != null) {
            buffer.append(", creditmanSlowMarkPrice=").append(creditmanSlowMarkPrice);
        }
        if (delayedBidOption != null) {
            buffer.append(", delayedBidOption=").append(delayedBidOption);
        }
        if (delayedAskOption != null) {
            buffer.append(", delayedAskOption=").append(delayedAskOption);
        }
        if (delayedLastOption != null) {
            buffer.append(", delayedLastOption=").append(delayedLastOption);
        }
        if (delayedModelOption != null) {
            buffer.append(", delayedModelOption=").append(delayedModelOption);
        }
        if (bidExchange != null) {
            buffer.append(", bidExchange='").append(bidExchange).append('\'');
        }
        if (askExchange != null) {
            buffer.append(", askExchange='").append(askExchange).append('\'');
        }
        if (lastTimestamp != null) {
            buffer.append(", lastTimestamp='").append(lastTimestamp).append('\'');
        }
        if (rtVolume != null) {
            buffer.append(", rtVolume='").append(rtVolume).append('\'');
        }
        if (ibDividends != null) {
            buffer.append(", ibDividends='").append(ibDividends).append('\'');
        }
        if (news != null) {
            buffer.append(", news='").append(news).append('\'');
        }
        if (rtTradeVolume != null) {
            buffer.append(", rtTradeVolume='").append(rtTradeVolume).append('\'');
        }
        if (lastExchange != null) {
            buffer.append(", lastExchange='").append(lastExchange).append('\'');
        }
        if (lastRegulatoryTime != null) {
            buffer.append(", lastRegulatoryTime='").append(lastRegulatoryTime).append('\'');
        }
        buffer.append('}');
        return buffer.toString();
    }
}
