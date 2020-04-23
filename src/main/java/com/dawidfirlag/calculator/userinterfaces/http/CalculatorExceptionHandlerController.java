package com.dawidfirlag.calculator.userinterfaces.http;

import com.dawidfirlag.calculator.application.dto.ErrorResponseDto;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CalculatorExceptionHandlerController {

    @ExceptionHandler(InvalidExpressionException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto invalidExpressionHandler(InvalidExpressionException ex) {
        return new ErrorResponseDto(ex.getMessage());
    }

    @ExceptionHandler(DividedByZeroException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto dividedByZeroHandler(DividedByZeroException ex) {
        return new ErrorResponseDto("Divided by zero");
    }
}
