package ustu.is.InternetLab.calculator.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorDel implements Calculator {
    private final Logger log = LoggerFactory.getLogger(CalculatorDel.class);

    @Override
    public String calc(int a, int b){
        return "I am del:  Your del (" + a + " / " + b + "): " + ((float)a/(float)b);
    }

    public void init(){
        log.info("CalculatorDel.init()");
    }

    public void destroy(){
        log.info("CalculatorDel.destroy()");
    }
}
