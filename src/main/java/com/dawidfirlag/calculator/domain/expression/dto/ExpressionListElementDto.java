package com.dawidfirlag.calculator.domain.expression.dto;

public class ExpressionListElementDto {
    private final long id;
    private final String expression;
    private final String result;

    public ExpressionListElementDto(long id, String expression, String result) {
        this.id = id;
        this.expression = expression;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public String getExpression() {
        return expression;
    }

    public String getResult() {
        return result;
    }
}
