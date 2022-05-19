package ustu.is.InternetLab.test.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ustu.is.InternetLab.test.model.TestDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping
    public TestDto testValidation(@RequestBody @Valid TestDto testDto) {
        return testDto;
    }
}