package main.java.data;

import org.joda.time.DateTime;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * Created by liviu on 11/14/2015.
 */
public class Trade {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy:HH:mm");

    private DateTime executionTimestamp ;
    private Integer numberOfShares;
    private String buyOrSell;
    private Double tradePrice;

    public Trade(String executionTimestamp, String numberOfShares, String buyOrSell, String tradePrice) {
        this.executionTimestamp = DateTime.parse(executionTimestamp, dateTimeFormatter);;
        this.numberOfShares = Integer.parseInt(numberOfShares);
        this.buyOrSell = buyOrSell;
        this.tradePrice = Double.parseDouble(tradePrice);
    }

    public DateTime getExecutionTimestamp() {
        return executionTimestamp;
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
        this.executionTimestamp = DateTime.parse(executionTimestamp, dateTimeFormatter);
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
