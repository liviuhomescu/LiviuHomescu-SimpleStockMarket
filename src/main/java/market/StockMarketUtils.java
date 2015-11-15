package main.java.market;

import main.java.data.Stock;
import main.java.data.Trade;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liviu on 11/10/2015.
 */
public class StockMarketUtils {

    private HashMap stockMarket;
    private List<Trade> stockMarketTrades = new ArrayList<>();

    public StockMarketUtils (){
        stockMarket = new HashMap<String, Stock>();
        stockMarket.put("TEA", new Stock("TEA", "Common", 0, null, 100));
        stockMarket.put("POP", new Stock("POP", "Common", 8, null, 100));
        stockMarket.put("ALE", new Stock("ALE", "Common", 23, null, 60));
        stockMarket.put("GIN", new Stock("GIN", "Preferred", 8, "2", 100));
        stockMarket.put("JOE", new Stock("TEA", "Common", 13, null, 100));
    }

    public synchronized void addStock(Stock stock){
        stockMarket.put(stock.getStockSymbol(), stock);
    }

    public Stock getStock(String stockSymbol){
       return (Stock) stockMarket.get(stockSymbol);
    }

    public synchronized void addTrade(Trade trade){
        stockMarketTrades.add(trade);
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

    public String calculateVolumeWeightedStockPrice(){
        int sumOfShares = 0;
        double wheightedSum = 0.0;
        List<Trade> pastTrades = getTradesInThePast(15);
        for (Trade trade : pastTrades) {
            sumOfShares += trade.getNumberOfShares();
            wheightedSum += trade.getNumberOfShares() * trade.getTradePrice();
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
