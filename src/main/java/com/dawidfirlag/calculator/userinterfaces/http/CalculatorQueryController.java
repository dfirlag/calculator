package com.dawidfirlag.calculator.userinterfaces.http;

import com.dawidfirlag.calculator.application.CalculatorQueryService;
import com.dawidfirlag.calculator.domain.interpreter.dto.ResultDto;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@CrossOrigin
@RestController
public class CalculatorQueryController {

    @Autowired
    private CalculatorQueryService calculatorQueryService;

    @GetMapping(path = "/calculator/get-calculated-expression")
    public ResultDto getCalculatedExpression(@RequestParam String expression) {
        expression = URLDecoder.decode(expression, StandardCharsets.UTF_8);

        try {
            return this.calculatorQueryService.calculateExpressionFromString(expression);
        } catch (InvalidExpressionException | DividedByZeroException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
