package ustu.is.InternetLab.calculator.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorMult implements Calculator{
    private final Logger log = LoggerFactory.getLogger(CalculatorMult.class);

    @Override
    public String calc(int a, int b){
        return "I am mult:  Your mult (" + a + " * " + b + "): " + (a*b);
    }

    public void init(){
        log.info("CalculatorMult.init()");
    }

    public void destroy(){
        log.info("CalculatorMult.destroy()");
    }
}
