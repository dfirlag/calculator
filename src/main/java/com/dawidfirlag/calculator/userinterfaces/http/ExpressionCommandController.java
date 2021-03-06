package com.dawidfirlag.calculator.userinterfaces.http;

import com.dawidfirlag.calculator.application.ExpressionCommandService;
import com.dawidfirlag.calculator.domain.expression.dto.ExpressionDto;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/expression")
public class ExpressionCommandController {

    private final ExpressionCommandService expressionCommandService;

    @Autowired
    public ExpressionCommandController(ExpressionCommandService expressionCommandService) {
        this.expressionCommandService = expressionCommandService;
    }

    @PostMapping(value = "/save-expression", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExpression(@RequestBody ExpressionDto expression)
        throws InvalidExpressionException, DividedByZeroException {
        this.expressionCommandService.saveExpression(expression.getExpression());
    }

    @DeleteMapping(value = "/delete-expression/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExpression(@PathVariable("id") Long id) {
        this.expressionCommandService.deleteExpression(id);
    }
}
