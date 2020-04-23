package com.dawidfirlag.calculator.application.dto;

public class ErrorResponseDto {

    private final String message;

    public ErrorResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
