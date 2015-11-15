package main.java.data.interpretors;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liviu on 11/13/2015.
 */

public class DividendCalculatorFactory {
    private Map<String, DividendCalculator> dividendCalculatorMap = new HashMap<>();

    public DividendCalculatorFactory(DividendCalculator commonDividendCalculator, DividendCalculator preferredDividendCalculator) {
           dividendCalculatorMap.put("Common", commonDividendCalculator);
           dividendCalculatorMap.put("Preferred", preferredDividendCalculator);
    }

    public DividendCalculator getDividendCalculator(String stockType) {
        return dividendCalculatorMap.get(stockType);
    }
}
