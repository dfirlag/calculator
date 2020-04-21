package com.dawidfirlag.calculator.domain.interpreter.instruction;

import com.dawidfirlag.calculator.domain.interpreter.AbstractInstruction;
import com.dawidfirlag.calculator.domain.interpreter.Operand;
import com.dawidfirlag.calculator.domain.interpreter.OperatorsPriority;

public class MultiplyInstruction extends AbstractInstruction {

    public MultiplyInstruction(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public double calculate() {
        return this.leftOperand.getDoubleValue() * this.rightOperand.getDoubleValue();
    }

    @Override
    public OperatorsPriority getPriorityOperator() {
        return OperatorsPriority.MULTIPLY;
    }
}
