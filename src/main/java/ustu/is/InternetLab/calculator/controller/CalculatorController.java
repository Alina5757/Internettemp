package ustu.is.InternetLab.calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ustu.is.InternetLab.configuration.WebConfiguration;
import ustu.is.InternetLab.calculator.service.CalculatorServise;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/calc")
public class CalculatorController {
    private final CalculatorServise calculatorServise;

    public CalculatorController(CalculatorServise calculatorServise){
        this.calculatorServise = calculatorServise;
    }

    @GetMapping("/")
    public String calc(@RequestParam(value = "name", defaultValue = "User") String name,
                       @RequestParam(value = "operation", defaultValue = "summ") String operation,
                       @RequestParam(value = "a", defaultValue = "10") int a,
                       @RequestParam(value = "b", defaultValue = "5") int b){
        return calculatorServise.say(name, operation, a, b);
    }
}