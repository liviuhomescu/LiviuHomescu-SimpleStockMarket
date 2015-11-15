package main.java.stock.unittests;

import main.java.data.Stock;
import main.java.data.interpretors.CommonDividendCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by liviu on 11/13/2015.
 */
public class PreferredDividendCalculatorTest {

    @Test()
    public void returnsInfinityWhenCalculatingDividendYieldForZeroPrice() {

        CommonDividendCalculator testedCalculator = new CommonDividendCalculator();
        Stock commonStock = new Stock("StockSymcol", "Preferred", 30, "3", 100);
        assertEquals("The correct value is Infinity" , String.valueOf(Double.POSITIVE_INFINITY), String.valueOf(testedCalculator.calculateDividendYield(commonStock, 0.0)));
    }

    @Test
    public void ReturnsNaNWhenCalculatingPERarioForZeroDividendAndZeroPrice() {

        CommonDividendCalculator testedCalculator = new CommonDividendCalculator();
        Stock commonStock = new Stock("StockSymcol", "Preferred", 0, "2", 100);
        assertEquals("The correct value is NAN" , String.valueOf(Double.NaN), String.valueOf(testedCalculator.calculatePERatio(commonStock, 0.0)));

    }
}
