package com.dawidfirlag.calculator.domain.interpreter;

abstract public class AbstractOperand implements Operand {

    protected boolean isNegativeOperand;

    @Override
    public void setAsNegativeOperand() {
        this.isNegativeOperand = true;
    }
}
