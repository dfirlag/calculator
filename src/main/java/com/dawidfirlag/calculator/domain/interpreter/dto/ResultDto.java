package com.dawidfirlag.calculator.domain.interpreter.dto;

public class ResultDto {

    private final double result;

    public ResultDto(double result) {
        this.result = result;
    }

    public double getDouble() {
        return result;
    }
}
