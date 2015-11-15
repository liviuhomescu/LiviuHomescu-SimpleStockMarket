package main.java.data.interpretors;

import main.java.data.Stock;

/**
 * Created by liviu on 11/13/2015.
 */
public class CommonDividendCalculator implements DividendCalculator{

    @Override
    public Double calculateDividendYield(Stock stock, Double price) {
        return stock.getLastDividend() / price ;
    }

    @Override
    public Double calculatePERatio(Stock stock, Double price) {
        return price /  stock.getLastDividend();
    }
}
