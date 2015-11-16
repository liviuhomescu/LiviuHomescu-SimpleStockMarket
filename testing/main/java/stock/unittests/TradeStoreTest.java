package main.java.stock.unittests;

import main.java.data.Stock;
import main.java.data.Trade;
import main.java.market.StockMarketUtils;
import main.java.market.TradeStore;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by liviu on 11/16/2015.
 */
public class TradeStoreTest{

    StockMarketUtils stockMarket;

    @Before
    public void setup(){
        stockMarket = new StockMarketUtils();
        stockMarket.addStock(new Stock("TEA", "Common", 0, null, 100));
        stockMarket.addStock(new Stock("POP", "Common", 8, null, 100));
        stockMarket.addStock(new Stock("ALE", "Common", 23, null, 60));
        stockMarket.addStock(new Stock("GIN", "Preferred", 8, "2", 100));
        stockMarket.addStock(new Stock("TEA", "Common", 13, null, 100));
     }
    @Test
    public void returnsEmptyListWhenTheArentAnyTrades(){
        TradeStore tradeStore = new TradeStore(stockMarket);
        assertEquals("Should be an ampty list", new ArrayList<Trade>(), tradeStore.getTradesInThePast(100));
    }

    @Test
    public void returnsEmptyListWhenMinutesIsZero(){

        TradeStore tradeStore = new TradeStore(stockMarket);

        Trade testTrade = new Trade("03-04-2015:04:30", "256","buy", "1.2", "ALE");

        tradeStore.addTrade(testTrade);

        assertEquals("Should be an empty list", new ArrayList<Trade>(), tradeStore.getTradesInThePast(0));

    }

    @Test
    public void returnsTradesCorrectly(){

        TradeStore tradeStore = new TradeStore(stockMarket);
        Trade testTrade = new Trade(DateTime.now().minus(15).toString("dd-MM-yyyy:HH:mm"), "256","buy", "1.2", "POP");

        tradeStore.addTrade(testTrade);

        assertEquals("Should be list with one trade", 1, tradeStore.getTradesInThePast(15).size());

    }

    @Test
    public void volumeWheightedStockPriceReturnsNANwhenThereAreNoTrades() {
        TradeStore tradeStore = new TradeStore(stockMarket);
        assertEquals("Should be NaN", "NaN", tradeStore.calculateVolumeWeightedStockPrice("ALE"));
    }

    @Test
    public void calculateGeometricMeanOfAllIndicesCorrectly() {
        TradeStore tradeStore = new TradeStore(stockMarket);

        Trade testTrade = new Trade(DateTime.now().minus(15).toString("dd-MM-yyyy:HH:mm"), "256","buy", "1.0", "ALE");
        Trade secondTestTrade = new Trade(DateTime.now().minus(15).toString("dd-MM-yyyy:HH:mm"), "256","buy", "4.0", "ALE");
        tradeStore.addTrade(secondTestTrade);
        tradeStore.addTrade(testTrade);

        assertEquals("Should be 2.0", "2.0", tradeStore.calculateGeometricMeanOfAllIndices());
    }

    @Test
    public void calculateGeometricMeanOfAllIndicesReturnsNANwhenThereAreNoTrades() {
        TradeStore tradeStore = new TradeStore(stockMarket);
        assertEquals("Should be NaN", "NaN", tradeStore.calculateGeometricMeanOfAllIndices());
    }

    @Test(expected=RuntimeException.class)
    public void throwsExceptionWhenTimestampDoesNotMatch(){
        Trade trade = new Trade("03-04-2015:25:61", "buy", "256", "1.2", "PXXXX");
        TradeStore tradeStore = new TradeStore(stockMarket);
        tradeStore.addTrade(trade);
    }
}
