package com.dawidfirlag.calculator.domain.interpreter;

abstract public class AbstractInstruction implements Instruction {

    protected Operand leftOperand;
    protected Operand rightOperand;

    public AbstractInstruction(Operand leftOperand, Operand rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public void updateLeftOperand(Operand leftOperand) {
        this.leftOperand = leftOperand;
    }

    @Override
    public void updateRightOperand(Operand rightOperand) {
        this.rightOperand = rightOperand;
    }

    @Override
    public Operand getLeftOperand() {
        return leftOperand;
    }

    @Override
    public Operand getRightOperand() {
        return rightOperand;
    }
}
