package ustu.is.InternetLab.calculator.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ustu.is.InternetLab.calculator.domain.*;

@Configuration
public class CalculatorConfiguration {
    private final Logger log = LoggerFactory.getLogger(CalculatorConfiguration.class);

    @Bean(value = "summ", initMethod = "init", destroyMethod = "destroy")
    public Calculator createSumm(){
        log.info("Call createSumm()");
        return new CalculatorSumm();
    }

    @Bean(value = "razn", initMethod = "init", destroyMethod = "destroy")
    public Calculator createRazn(){
        log.info("Call createRazn()");
        return new CalculatorRazn();
    }

    @Bean(value = "mult", initMethod = "init", destroyMethod = "destroy")
    public Calculator createMult(){
        log.info("Call createMult()");
        return new CalculatorMult();
    }

    @Bean(value = "del", initMethod = "init", destroyMethod = "destroy")
    public Calculator createDel(){
        log.info("Call createDel()");
        return new CalculatorDel();
    }
}