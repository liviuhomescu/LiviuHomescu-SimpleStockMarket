package main.java.configuration;

import main.java.data.interpretors.CommonDividendCalculator;
import main.java.data.interpretors.DividendCalculatorFactory;
import main.java.data.interpretors.PreferredDividendCalculator;
import main.java.market.StockMarketUtils;
import main.java.market.TradeStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liviu on 11/13/2015.
 */
@Configuration
public class ApplicationContext {

    @Bean
    public DividendCalculatorFactory dividendCalculatorFactory(){
        return new DividendCalculatorFactory(new CommonDividendCalculator(), new PreferredDividendCalculator());
    }

    @Bean
    public StockMarketUtils stockMarketUtils(){
        return new StockMarketUtils();
    }

    @Bean
    public TradeStore TradeStore(){
        return new TradeStore(stockMarketUtils());
    }
}
