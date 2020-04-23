package com.dawidfirlag.calculator.userinterfaces.http;

import com.dawidfirlag.calculator.application.ExpressionCommandService;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/expression")
public class ExpressionCommandController {

    @Autowired
    ExpressionCommandService calculatorCommandService;

    @PutMapping("/save-expression")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExpression(@RequestParam String expression)
        throws InvalidExpressionException, DividedByZeroException
    {
        this.calculatorCommandService.saveExpression(expression);
    }

}
