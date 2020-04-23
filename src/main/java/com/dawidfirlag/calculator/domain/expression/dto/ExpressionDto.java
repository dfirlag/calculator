package com.dawidfirlag.calculator.domain.expression.dto;

public class ExpressionDto {
    private String expression;

    public ExpressionDto() {}

    public ExpressionDto(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
