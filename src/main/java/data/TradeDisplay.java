package main.java.data;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * Created by liviu on 11/14/2015.
 */
public class TradeDisplay {

    private String executionTimestampDisplay ;
    private Integer numberOfShares;
    private String buyOrSell;
    private Double tradePrice;

    public String getExecutionTimestamp() {
        return executionTimestampDisplay;
    }

    public Integer getNumberOfShares() {
        return numberOfShares;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public Double getTradePrice() {
        return tradePrice;
    }


    public void setExecutionTimestamp(String executionTimestamp) {
        this.executionTimestampDisplay = executionTimestamp;
    }

    public void setNumberOfShares(Integer numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell.toLowerCase();
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }
}
