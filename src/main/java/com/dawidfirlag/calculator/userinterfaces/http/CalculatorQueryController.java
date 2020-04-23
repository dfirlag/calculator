package com.dawidfirlag.calculator.userinterfaces.http;

import com.dawidfirlag.calculator.application.CalculatorQueryService;
import com.dawidfirlag.calculator.domain.interpreter.dto.ResultDto;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@CrossOrigin
@RestController
@RequestMapping(path = "/calculator")
public class CalculatorQueryController {

    @Autowired
    private CalculatorQueryService calculatorQueryService;

    @GetMapping(path = "/get-calculated-expression", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto getCalculatedExpression(@RequestParam String expression)
        throws InvalidExpressionException, DividedByZeroException
    {
        return this.calculatorQueryService.calculateExpressionFromString(
            URLDecoder.decode(expression, StandardCharsets.UTF_8)
        );
    }
}
