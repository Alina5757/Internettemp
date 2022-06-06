package ustu.is.InternetLab.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ustu.is.InternetLab.calculator.service.CalculatorServise;

import java.util.List;

@Controller
@RequestMapping("/calc")
public class CalculatorMvcController {

    private CalculatorServise calculatorServise;
    private final List<String> operates;

    public CalculatorMvcController(CalculatorServise calculatorService) {
        this.calculatorServise = calculatorService;
        this.operates = List.of("summ", "razn", "mult", "del");
    }

    @GetMapping
    public String calc(@RequestParam(value = "name", defaultValue = "User") String name,
                       @RequestParam(value = "operation", defaultValue = "summ") String operation,
                       @RequestParam(value = "a", defaultValue = "10") int a,
                       @RequestParam(value = "b", defaultValue = "5") int b,
                       Model model) {
        model.addAttribute("operates", operates);
        model.addAttribute("operation", operation);
        model.addAttribute("name", name);
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("calculate", calculatorServise.say(name, operation, a, b));
        return "calc";
    }
}