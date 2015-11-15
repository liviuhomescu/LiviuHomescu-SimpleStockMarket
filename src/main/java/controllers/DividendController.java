package main.java.controllers;

import main.java.data.interpretors.DividendCalculatorFactory;
import main.java.data.Stock;
import main.java.market.StockMarketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DividendController {
    @Autowired
    private DividendCalculatorFactory dividendCalculatorFactory;

    @Autowired
    private StockMarketUtils stockMarketUtils;

    @RequestMapping(method= RequestMethod.GET, value="/getDividend.htm/stock={stock}&price={price}")
    public @ResponseBody String getDividend(@PathVariable String stock, @PathVariable String price, Model model){
        Double dividendYield = getDividendYield(stock, price);
        return String.valueOf(dividendYield);
    }

    @RequestMapping(method= RequestMethod.GET, value="/getPERatio.htm/stock={stock}&price={price}")
    public @ResponseBody String getPERatio(@PathVariable String stock, @PathVariable String price, Model model){
        Double dividendYield = calculatePERatio(stock, price);
        return String.valueOf(dividendYield);
    }

    private Double getDividendYield(String stock, String price) {
        Stock marketStock = stockMarketUtils.getStock(stock);
        Double priceDouble = Double.parseDouble(price);
        if (marketStock == null){
            return -1.0;
        }
        return dividendCalculatorFactory.getDividendCalculator(marketStock.getType()).calculateDividendYield(marketStock, priceDouble);
    }

    private Double calculatePERatio(String stock, String price) {
        Stock marketStock = stockMarketUtils.getStock(stock);
        Double priceDouble = Double.parseDouble(price);
        if (marketStock == null){
            return -1.0;
        }
        return dividendCalculatorFactory.getDividendCalculator(marketStock.getType()).calculatePERatio(marketStock, priceDouble);
    }
}
