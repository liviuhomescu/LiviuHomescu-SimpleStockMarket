package main.java.data.interpretors;

import main.java.data.Stock;

/**
 * Created by liviu on 11/13/2015.
 */
public class PreferredDividendCalculator implements DividendCalculator{

    @Override
    public Double calculateDividendYield(Stock stock, Double price) {
        return stock.getFixedDividend() * stock.getParValue() / price ;
    }

    @Override
    public Double calculatePERatio(Stock stock, Double price) {
        return price /(stock.getFixedDividend() * stock.getParValue()) ;

    }
}
