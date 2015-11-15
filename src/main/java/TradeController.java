package main.java;

import main.java.data.Trade;
import main.java.data.TradeDisplay;
import main.java.market.StockMarketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liviu on 11/14/2015.
 */
@Controller
public class TradeController{

    @Autowired
    private StockMarketUtils stockMarketUtils;

    @RequestMapping(method= RequestMethod.GET, value="/addTrade.htm/executionTimestamp={executionTimestamp}&numberOfShares={numberOfShares}&buyOrSell={buyOrSell}&tradePrice={tradePrice}")
    public @ResponseBody String addTrade(@PathVariable String executionTimestamp, @PathVariable String numberOfShares,
                                            @PathVariable String buyOrSell, @PathVariable String tradePrice, Model model){

        stockMarketUtils.addTrade(new Trade(executionTimestamp, numberOfShares, buyOrSell, tradePrice));
        return null;
    }

    @RequestMapping(method= RequestMethod.GET, value="/trades.htm")
    public String trades(Model model){
        model.addAttribute("command", new TradeDisplay());
        return "addTrade" ;
    }

    @RequestMapping(method= RequestMethod.GET, value="/volumeWeightedStockPrice.htm")
    public @ResponseBody String volumeWeightedStockPrice(Model model){
        return stockMarketUtils.calculateVolumeWeightedStockPrice();
    }

    @RequestMapping(method= RequestMethod.GET, value="/calculateGeometricMeanOfAllIndices.htm")
    public @ResponseBody String calculateGeometricMeanOfAllIndices(Model model){
        return stockMarketUtils.calculateGeometricMeanOfAllIndices();
    }
}
