package com.dawidfirlag.calculator.domain.interpreter.instruction;

import com.dawidfirlag.calculator.domain.interpreter.AbstractInstruction;
import com.dawidfirlag.calculator.domain.interpreter.Operand;
import com.dawidfirlag.calculator.domain.interpreter.OperatorsPriority;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;

public class DivideInstruction extends AbstractInstruction {

    public DivideInstruction(Operand leftOperand, Operand rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public double calculate() throws DividedByZeroException {
        if (this.rightOperand.getDoubleValue() == 0.0) {
            throw new DividedByZeroException();
        }

        return this.leftOperand.getDoubleValue() / this.rightOperand.getDoubleValue();
    }

    @Override
    public OperatorsPriority getPriorityOperator() {
        return OperatorsPriority.DIVIDE;
    }
}
