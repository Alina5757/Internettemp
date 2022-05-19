package ustu.is.InternetLab.calculator.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class CalculatorRazn implements Calculator {
    private final Logger log = LoggerFactory.getLogger(CalculatorRazn.class);

    @Override
    public String calc(int a, int b){
        return "I am razn:  Your razn (" + a + " - " + b + "): " + (a-b);
    }

    @PostConstruct
    public void init(){
        log.info("CalculatorRazn.init()");
    }

    @PreDestroy
    public void destroy(){
        log.info("CalculatorRazn.destroy()");
    }
}
