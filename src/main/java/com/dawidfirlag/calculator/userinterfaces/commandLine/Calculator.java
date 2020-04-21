package com.dawidfirlag.calculator.userinterfaces.commandLine;

import com.dawidfirlag.calculator.application.CalculatorQueryService;
import com.dawidfirlag.calculator.domain.interpreter.dto.ResultDto;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;

public class Calculator {
    public static void main(String[] arg) {
        CalculatorQueryService calculatorQueryService = new CalculatorQueryService();
        ResultDto result;
        try {
            result = calculatorQueryService.calculateExpressionFromStringArray(arg);
        } catch (InvalidExpressionException e) {
            System.out.println("Invalid expression: " + e.toString());
            System.exit(0);
            return;
        } catch (DividedByZeroException e) {
            System.out.println("Divided by zero: " + e.toString());
            System.exit(0);
            return;
        }

        System.out.println("Result: " + result.getDouble());
        System.exit(1);
    }
}
