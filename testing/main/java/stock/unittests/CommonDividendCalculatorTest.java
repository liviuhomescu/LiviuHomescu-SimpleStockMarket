package main.java.stock.unittests;
/**
 * Created by liviu on 11/13/2015.
 */

import main.java.data.interpretors.CommonDividendCalculator;
import main.java.data.Stock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommonDividendCalculatorTest {

    @Test()
    public void returnsInfinityWhenCalculatingDividendYieldForZeroPrice() {

        CommonDividendCalculator testedCalculator = new CommonDividendCalculator();
        Stock commonStock = new Stock("StockSymcol", "Common", 30, null, 100);
        assertEquals("The correct value is Infinity" , String.valueOf(Double.POSITIVE_INFINITY), String.valueOf(testedCalculator.calculateDividendYield(commonStock, 0.0)));
    }

    @Test
    public void ReturnsNaNWhenCalculatingPERarioForZeroDividendAndZeroPrice() {

        CommonDividendCalculator testedCalculator = new CommonDividendCalculator();
        Stock commonStock = new Stock("StockSymcol", "Common", 0, null, 100);
        assertEquals("The correct value is NAN" , String.valueOf(Double.NaN), String.valueOf(testedCalculator.calculatePERatio(commonStock, 0.0)));

    }

}
