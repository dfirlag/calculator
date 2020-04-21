package com.dawidfirlag.calculator.domain.interpreter;

import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum Operators {
    DIVIDE('/'),
    MULTIPLY('*'),
    SUBTRACT('-'),
    ADD('+');

    private final char operator;

    Operators(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return this.operator;
    }

    public static Pattern getAvailableOperatorsPattern() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        Arrays.stream(Operators.values())
            .forEach(operator -> stringBuilder.append('\\').append(operator.getOperator()));

        stringBuilder.append("]{1}");

        return Pattern.compile(stringBuilder.toString());
    }

    public static Operators getByValue(char value) throws InvalidExpressionException {
        return switch (value) {
            case '/' -> Operators.DIVIDE;
            case '*' -> Operators.MULTIPLY;
            case '+' -> Operators.ADD;
            case '-' -> Operators.SUBTRACT;
            default -> throw new InvalidExpressionException("Invalid operator: " + value);
        };
    }
}
