package com.dawidfirlag.calculator.domain.interpreter.factory;

import com.dawidfirlag.calculator.domain.interpreter.Instruction;
import com.dawidfirlag.calculator.domain.interpreter.Operand;
import com.dawidfirlag.calculator.domain.interpreter.Operators;
import com.dawidfirlag.calculator.domain.interpreter.exception.InvalidInstructionException;
import com.dawidfirlag.calculator.domain.interpreter.instruction.*;

public class InstructionFactory {

    public Instruction createInstructionByString(
            Operators instruction,
            Operand leftOperand,
            Operand rightOperand
    ) throws InvalidInstructionException
    {
        return switch (instruction) {
            case MULTIPLY -> new MultiplyInstruction(leftOperand, rightOperand);
            case DIVIDE -> new DivideInstruction(leftOperand, rightOperand);
            case ADD -> new AddInstruction(leftOperand, rightOperand);
            case SUBTRACT -> new SubtractInstruction(leftOperand, rightOperand);
            default -> throw new InvalidInstructionException();
        };
    }
}
