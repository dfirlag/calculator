package com.dawidfirlag.calculator.userinterfaces.http;

import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CalculatorControllerAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidExpressionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String employeeNotFoundHandler(InvalidExpressionException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DividedByZeroException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String employeeNotFoundHandler(DividedByZeroException ex) {
        return ex.getMessage();
    }
}
