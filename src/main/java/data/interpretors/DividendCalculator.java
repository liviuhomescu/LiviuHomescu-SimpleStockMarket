package main.java.data.interpretors;

import main.java.data.Stock;

/**
 * Created by liviu on 11/13/2015.
 */
public interface DividendCalculator {
    Double calculateDividendYield(Stock stock, Double price);
    Double calculatePERatio(Stock stock, Double price);
}
