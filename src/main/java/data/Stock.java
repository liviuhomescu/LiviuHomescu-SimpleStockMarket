package main.java.data;/**
 * Created by liviu on 11/10/2015.
 */
public class Stock {

    private String stockSymbol;
    private int lastDividend;
    private String type;
    private Double fixedDividend;
    private int parValue;

    public Stock(String stockSymbol, String type, int lastDividend, String fixedDividendString, int parValue) {
        this.stockSymbol = stockSymbol;
        this.lastDividend = lastDividend;
        this.type = type;
        this.fixedDividend = fixedDividendString ==null ? null : Double.valueOf(fixedDividendString) / 100;
        this.parValue = parValue;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(int lastDividend) {
        this.lastDividend = lastDividend;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(Double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public int getParValue() {
        return parValue;
    }

    public void setParValue(int parValue) {
        this.parValue = parValue;
    }
}
