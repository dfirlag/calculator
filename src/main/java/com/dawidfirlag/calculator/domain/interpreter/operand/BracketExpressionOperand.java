package com.dawidfirlag.calculator.domain.interpreter.operand;

import com.dawidfirlag.calculator.domain.interpreter.AbstractOperand;
import com.dawidfirlag.calculator.domain.interpreter.Instruction;

import java.util.LinkedList;

public class BracketExpressionOperand extends AbstractOperand {

    private final LinkedList<Instruction> instructions;

    public BracketExpressionOperand() {
        this.instructions = new LinkedList<>();
    }

    public LinkedList<Instruction> getInstructionsList() {
        return instructions;
    }

    @Override
    public double getDoubleValue() {
        throw new RuntimeException("Try to get double value from expression operand");
    }
}
