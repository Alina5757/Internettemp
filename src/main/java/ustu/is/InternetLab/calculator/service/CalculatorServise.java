package ustu.is.InternetLab.calculator.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ustu.is.InternetLab.calculator.domain.Calculator;

@Service
public class CalculatorServise {
    private final ApplicationContext applicationContext;
    
    public CalculatorServise(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
    
    public String say(String name, String operation, int a, int b){
        final Calculator calculator = (Calculator)applicationContext.getBean(operation);
        return String.format("%s, %s!", calculator.calc( a, b), name);
    }
}
