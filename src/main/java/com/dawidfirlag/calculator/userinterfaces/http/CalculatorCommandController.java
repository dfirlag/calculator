package com.dawidfirlag.calculator.userinterfaces.http;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorCommandController {

    @PutMapping("/calculator/save-expression")
    public void saveExpression(@RequestBody String expression) {

    }
}
