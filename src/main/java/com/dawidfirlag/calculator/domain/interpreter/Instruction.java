package com.dawidfirlag.calculator.domain.interpreter;

import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;

public interface Instruction {

    OperatorsPriority getPriorityOperator();
    void updateLeftOperand(Operand leftOperand);
    void updateRightOperand(Operand rightOperand);
    Operand getLeftOperand();
    Operand getRightOperand();
    double calculate() throws DividedByZeroException;
}
