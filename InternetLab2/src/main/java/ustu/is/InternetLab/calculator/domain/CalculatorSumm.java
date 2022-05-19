package ustu.is.InternetLab.calculator.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorSumm implements Calculator {
    private final Logger log = LoggerFactory.getLogger(CalculatorSumm.class);

    @Override
    public String calc(int a, int b){
        return "I am summ:  Your summ (" + a + " + " + b + "): " + (a+b);
    }

    public void init(){
        log.info("CalculatorSumm.init()");
    }

    public void destroy(){
        log.info("CalculatorSumm.destroy()");
    }
}
