package com.dawidfirlag.calculator.domain.interpreter;

import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.interpreter.instruction.AddInstruction;
import com.dawidfirlag.calculator.domain.interpreter.instruction.DivideInstruction;
import com.dawidfirlag.calculator.domain.interpreter.instruction.MultiplyInstruction;
import com.dawidfirlag.calculator.domain.interpreter.instruction.SubtractInstruction;
import com.dawidfirlag.calculator.domain.interpreter.operand.ValueOperand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class InterpreterTest {

    private final Interpreter interpreter = new Interpreter();

    @Test
    public void testInterpreterInstructionValidResult() throws DividedByZeroException {
        LinkedList<Instruction> instructions = this.interpreter.getInstructionsList();

        // 1 + 2 / 3 * 5 - 10
        instructions.add(new AddInstruction(new ValueOperand(1), new ValueOperand(2)));
        instructions.add(new DivideInstruction(new ValueOperand(2), new ValueOperand(3)));
        instructions.add(new MultiplyInstruction(new ValueOperand(3), new ValueOperand(5)));
        instructions.add(new SubtractInstruction(new ValueOperand(5), new ValueOperand(10)));

        this.interpreter.calculate();
        Assertions.assertEquals(-5.666666666666667, this.interpreter.getResult().getDouble(), 0.0);
    }
}
