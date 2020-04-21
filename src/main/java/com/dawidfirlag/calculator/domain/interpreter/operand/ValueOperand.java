package com.dawidfirlag.calculator.domain.interpreter.operand;

import com.dawidfirlag.calculator.domain.interpreter.AbstractOperand;

public class ValueOperand extends AbstractOperand {
    private final double value;

    public ValueOperand(double value) {
        this.value = value;
    }

    @Override
    public double getDoubleValue() {
        return this.isNegativeOperand ? -this.value : this.value;
    }
}
