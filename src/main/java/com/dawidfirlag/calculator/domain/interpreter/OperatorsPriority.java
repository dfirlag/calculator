package com.dawidfirlag.calculator.domain.interpreter;

public enum OperatorsPriority {
    BRACKET(0),
    DIVIDE(1),
    MULTIPLY(2),
    SUBTRACT(3),
    ADD(4);

    OperatorsPriority(int priority) {
    }
}
