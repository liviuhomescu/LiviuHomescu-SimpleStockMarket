package main.java.stock.unittests;

import main.java.data.Trade;
import main.java.data.TradeDisplay;
import main.java.market.StockMarketUtils;
import org.joda.time.IllegalFieldValueException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by liviu on 11/14/2015.
 */
public class TradeTest {

    @Test(expected=IllegalFieldValueException.class)
    public void throwsExceptionWhenTimestampDoesNotMatch(){

        Trade trade = new Trade("03-04-2015:25:61", "buy", "256", "1.2");

    }

    @Test(expected=IllegalArgumentException.class)
    public void throwsExceptionWhenTimestampHasWrongFormat(){

        Trade trade = new Trade("03-04-2015:25:61", "buy", "256", "1.2");

    }
}
