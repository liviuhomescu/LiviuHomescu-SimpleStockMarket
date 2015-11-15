package main.java.stock.unittests;

import main.java.data.Trade;
import main.java.data.TradeDisplay;
import main.java.market.StockMarketUtils;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by liviu on 11/14/2015.
 */
public class StockMarketUtilsTest {

    @Test
    public void returnsEmptyListWhenTheArentAnyTrades(){
        StockMarketUtils stockMarketUtils = new StockMarketUtils();
        assertEquals("Should be an ampty list", new ArrayList<Trade>(), stockMarketUtils.getTradesInThePast(100));
    }

    @Test
    public void returnsEmptyListWhenMinutesIsZero(){

        StockMarketUtils stockMarketUtils = new StockMarketUtils();

        Trade testTrade = new Trade("03-04-2015:04:30", "256","buy", "1.2");

        stockMarketUtils.addTrade(testTrade);

        assertEquals("Should be an ampty list", new ArrayList<Trade>(), stockMarketUtils.getTradesInThePast(0));

    }

    @Test
    public void returnsTradesCorrectly(){

        StockMarketUtils stockMarketUtils = new StockMarketUtils();
        Trade testTrade = new Trade(DateTime.now().minus(15).toString("dd-MM-yyyy:HH:mm"), "256","buy", "1.2");

        stockMarketUtils.addTrade(testTrade);

        assertEquals("Should be list with one trade", 1, stockMarketUtils.getTradesInThePast(15).size());

    }

    @Test
    public void volumeWheightedStockPriceReturnsNANwhenThereAreNoTrades() {
        StockMarketUtils stockMarketUtils = new StockMarketUtils();
        assertEquals("Should be NaN", "NaN", stockMarketUtils.calculateVolumeWeightedStockPrice());
    }

    @Test
    public void calculateGeometricMeanOfAllIndicesCorrectly() {
        StockMarketUtils stockMarketUtils = new StockMarketUtils();

        Trade testTrade = new Trade(DateTime.now().minus(15).toString("dd-MM-yyyy:HH:mm"), "256","buy", "1.0");
        Trade secondTestTrade = new Trade(DateTime.now().minus(15).toString("dd-MM-yyyy:HH:mm"), "256","buy", "4.0");
        stockMarketUtils.addTrade(secondTestTrade);
        stockMarketUtils.addTrade(testTrade);

        assertEquals("Should be 2.0", "2.0", stockMarketUtils.calculateGeometricMeanOfAllIndices());
    }

    @Test
    public void calculateGeometricMeanOfAllIndicesReturnsNANwhenThereAreNoTrades() {
        StockMarketUtils stockMarketUtils = new StockMarketUtils();
        assertEquals("Should be NaN", "NaN", stockMarketUtils.calculateGeometricMeanOfAllIndices());
    }

}
