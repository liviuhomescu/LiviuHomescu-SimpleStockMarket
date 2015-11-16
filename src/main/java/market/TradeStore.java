package main.java.market;

import main.java.data.Trade;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liviu on 11/16/2015.
 */

public class TradeStore {

    @Autowired
    private StockMarketUtils stockMarketUtils;
    private List<Trade> stockMarketTrades = new ArrayList<>();

    public TradeStore(StockMarketUtils stockMarketUtils) {
        this.stockMarketUtils = stockMarketUtils;
    }

    public synchronized void addTrade(Trade trade){
            if (stockMarketUtils.ListOfStocksSymbols().contains(trade.getSymbol())){
                stockMarketTrades.add(trade);
            } else {
                throw new RuntimeException("symbol not found");
            }
        }

    public List<Trade> getTradesInThePast(int minutes){

        List resultTrades = new ArrayList();
        if (stockMarketTrades == null || stockMarketTrades.isEmpty()){
            return resultTrades;
        }
        if (minutes <= 0){
            return resultTrades;
        }
        for (Trade trade : stockMarketTrades){
            if (!DateTime.now().minusMinutes(minutes).isAfter(trade.getExecutionTimestamp())){
                resultTrades.add(trade);
            }
        }
        return resultTrades;
    }

    public String calculateVolumeWeightedStockPrice(String symbol){
        int sumOfShares = 0;
        double wheightedSum = 0.0;
        List<Trade> pastTrades = getTradesInThePast(15);
        for (Trade trade : pastTrades) {
            if (trade.getSymbol().equals(symbol)) {
                sumOfShares += trade.getNumberOfShares();
                wheightedSum += trade.getNumberOfShares() * trade.getTradePrice();
            }
        }
        return String.valueOf(wheightedSum / sumOfShares);
    }

    public String calculateGeometricMeanOfAllIndices(){
        double product = 1.0;
        for (Trade trade : stockMarketTrades){
            product = product * trade.getTradePrice();
        }
        return String.valueOf(Math.pow(product, 1.0 / (double)stockMarketTrades.size()));
    }

}
