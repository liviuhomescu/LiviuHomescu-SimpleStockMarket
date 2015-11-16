package main.java;

import main.java.data.Trade;
import main.java.market.TradeStore;
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
    private TradeStore tradeStore;

    @RequestMapping(method= RequestMethod.GET, value="/addTrade.htm/executionTimestamp={executionTimestamp}&numberOfShares={numberOfShares}&buyOrSell={buyOrSell}&tradePrice={tradePrice}&symbol={symbol}")
    public @ResponseBody String addTrade(@PathVariable String executionTimestamp, @PathVariable String numberOfShares,
                                            @PathVariable String buyOrSell, @PathVariable String tradePrice,
                                            @PathVariable String symbol, Model model){

        tradeStore.addTrade(new Trade(executionTimestamp, numberOfShares, buyOrSell, tradePrice, symbol));
        return null;
    }

    @RequestMapping(method= RequestMethod.GET, value="/volumeWeightedStockPrice.htm/symbol={symbol}")
    public @ResponseBody String volumeWeightedStockPrice(@PathVariable String symbol, Model model){
        return tradeStore.calculateVolumeWeightedStockPrice(symbol);
    }

    @RequestMapping(method= RequestMethod.GET, value="/calculateGeometricMeanOfAllIndices.htm")
    public @ResponseBody String calculateGeometricMeanOfAllIndices(Model model){
        return tradeStore.calculateGeometricMeanOfAllIndices();
    }
}
