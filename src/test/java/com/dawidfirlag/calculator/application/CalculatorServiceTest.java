package com.dawidfirlag.calculator.application;

import com.dawidfirlag.calculator.domain.interpreter.dto.ResultDto;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalculatorServiceTest {

    private CalculatorQueryService calculatorQueryService;

    @BeforeEach
    private void init() {
        calculatorQueryService = new CalculatorQueryService();
    }

    private HashMap<String, Double> getCorrectMathExpressionDataProvider() {
        HashMap<String, Double> expressionMap = new HashMap<>();
        expressionMap.put("100000 + 100000", 200000.0);
        expressionMap.put("1234 + 1234", 2468.0);
        expressionMap.put("1*-2", -2.0);
        expressionMap.put("1*2", 2.0);
        expressionMap.put("1 * 2", 2.0);
        expressionMap.put("41 + 9 - 2 / ( 5 + 90 / 345 ) * ( 1 - 2 ) * ( 2 + 23 + ( 34 - 78 ) * 4 )", -7.404958677685947);
        expressionMap.put("41+9-2/(5+90/345)*(1-2)*(2+23+(34-78)*4)", -7.404958677685947);
        expressionMap.put("-41+9-2/(5+90/345)*(1-2)*(2+23+(34-78)*4)", -89.40495867768595);
        expressionMap.put("41      +9-2/    (5+90/345)*(      1-2)*(2+23+(34-78      )*4)", -7.404958677685947);
        expressionMap.put("1+5*(2-1)", 6.0);
        return expressionMap;
    }

    @Test
    public void testCalculateExpressionsFormString() {
        HashMap<String, Double> expressionMap = this.getCorrectMathExpressionDataProvider();
        expressionMap
            .forEach((key, value) -> {
                double excepted = value;
                ResultDto actual;
                try {
                    actual = this.calculatorQueryService.calculateExpressionFromString(key);
                } catch (InvalidExpressionException | DividedByZeroException e) {
                     throw new RuntimeException(e.getMessage());
                }

                Assertions.assertEquals(excepted, actual.getDouble(), 0);
            });
    }

    @Test
    public void testCalculateExpressionsFormArrayString() {
        HashMap<String, Double> expressionMap = this.getCorrectMathExpressionDataProvider();
        expressionMap
            .forEach((key, value) -> {
                double excepted = value;
                ResultDto actual;
                try {
                    actual = this.calculatorQueryService.calculateExpressionFromStringArray(key.split(" "));
                } catch (InvalidExpressionException | DividedByZeroException e) {
                     throw new RuntimeException(e.getMessage());
                }

                Assertions.assertEquals(excepted, actual.getDouble(), 0);
            });
    }

    @Test
    public void testCalculateExpressionsFormStream() {
        HashMap<String, Double> expressionMap = this.getCorrectMathExpressionDataProvider();
        expressionMap
            .forEach((key, value) -> {
                double excepted = value;
                ResultDto actual;
                try (InputStream targetStream = new ByteArrayInputStream(key.getBytes());) {
                    actual = this.calculatorQueryService.calculateExpressionFromStream(targetStream);
                } catch (InvalidExpressionException | DividedByZeroException | IOException e) {
                    throw new RuntimeException(e.getMessage());
                }

                Assertions.assertEquals(excepted, actual.getDouble(), 0);
            });
    }

    private List<String> getIncorrectMathExpressionDataProvider() {
        List<String> invalidExpressionList = new ArrayList<>();
        invalidExpressionList.add("10");
        invalidExpressionList.add("");
        invalidExpressionList.add("1++");
        invalidExpressionList.add("1!");
        invalidExpressionList.add("--1");
        invalidExpressionList.add("((6+9)))");
        return invalidExpressionList;
    }

    @Test
    public void testCalculateInvalidExpressionsFormString() throws InvalidExpressionException, DividedByZeroException {
        List<String> invalidExpressionList = this.getIncorrectMathExpressionDataProvider();
        Assertions.assertThrows(InvalidExpressionException.class, () -> {
            for (String invalidExpression : invalidExpressionList) {
                this.calculatorQueryService.calculateExpressionFromString(invalidExpression);
            }
        });
    }

    private List<String> getDividedByZeroMathExpressionDataProvider() {
        List<String> invalidExpressionList = new ArrayList<>();
        invalidExpressionList.add("10 / 0");
        invalidExpressionList.add("10 / ( 1 - 1 )");
        return invalidExpressionList;
    }

    @Test
    public void testCalculateDividedByZeroExpressionsFormString() throws DividedByZeroException, InvalidExpressionException {
        List<String> invalidExpressionList = this.getDividedByZeroMathExpressionDataProvider();
        Assertions.assertThrows(DividedByZeroException.class, () -> {
            for (String s : invalidExpressionList) {
                this.calculatorQueryService.calculateExpressionFromString(s);
            }
        });
    }

}
