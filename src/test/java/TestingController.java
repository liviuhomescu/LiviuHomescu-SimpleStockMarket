package test.java;

import main.java.data.Stock;
import main.java.market.StockMarketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liviu on 11/11/2015.
 */
@Controller
public class TestingController {

    @Autowired
    private StockMarketUtils stockMarketUtils;

    @RequestMapping("/functionalTests.htm")
    public String functionalTests(Model model){
        model.addAttribute("message", "Hello JPMorgan");
        return null;
    }
    @RequestMapping(method= RequestMethod.GET, value="addStock.htm/stock={stock}}&type={type}&lastDividend={lastDividend}&fixedDividend={fixedDividend}&parValue={parValue}")
    public @ResponseBody String addStock(@PathVariable String stock, @PathVariable String type, @PathVariable String lastDividend, @PathVariable String fixedDividend, @PathVariable String parValue, Model model){
        //validate stock
        stockMarketUtils.addStock(new Stock(stock, type, Integer.valueOf(lastDividend), fixedDividend, Integer.valueOf(parValue)));
        return null;
    }

}
