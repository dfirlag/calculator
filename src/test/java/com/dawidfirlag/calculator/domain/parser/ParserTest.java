package com.dawidfirlag.calculator.domain.parser;

import com.dawidfirlag.calculator.domain.interpreter.Instruction;
import com.dawidfirlag.calculator.domain.interpreter.instruction.AddInstruction;
import com.dawidfirlag.calculator.domain.interpreter.instruction.DivideInstruction;
import com.dawidfirlag.calculator.domain.interpreter.instruction.MultiplyInstruction;
import com.dawidfirlag.calculator.domain.interpreter.instruction.SubtractInstruction;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;
import com.dawidfirlag.calculator.domain.parser.parserInput.StreamParserInput;
import com.dawidfirlag.calculator.domain.parser.parserInput.StringParserInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;

public class ParserTest {

    @Test
    public void testStringParserInputParsedInstructions() throws InvalidExpressionException {
        String inputStr = "1 + 2 / 3 * 5 - 10";
        ParserInput parserInput = new StringParserInput(inputStr);
        Parser parser = new Parser(parserInput);

        LinkedList<Instruction> instructions = new LinkedList<>();
        parser.parseInput(instructions);

        Instruction instruction = instructions.pollFirst();
        Assertions.assertEquals(1.0, instruction.getLeftOperand().getDoubleValue(), 0.0);
        Assertions.assertEquals(2.0, instruction.getRightOperand().getDoubleValue(), 0.0);
        Assertions.assertTrue(instruction instanceof AddInstruction);

        instruction = instructions.pollFirst();
        Assertions.assertEquals(2.0, instruction.getLeftOperand().getDoubleValue(), 0.0);
        Assertions.assertEquals(3.0, instruction.getRightOperand().getDoubleValue(), 0.0);
        Assertions.assertTrue(instruction instanceof DivideInstruction);

        instruction = instructions.pollFirst();
        Assertions.assertEquals(3.0, instruction.getLeftOperand().getDoubleValue(), 0.0);
        Assertions.assertEquals(5.0, instruction.getRightOperand().getDoubleValue(), 0.0);
        Assertions.assertTrue(instruction instanceof MultiplyInstruction);

        instruction = instructions.pollFirst();
        Assertions.assertEquals(5.0, instruction.getLeftOperand().getDoubleValue(), 0.0);
        Assertions.assertEquals(10.0, instruction.getRightOperand().getDoubleValue(), 0.0);
        Assertions.assertTrue(instruction instanceof SubtractInstruction);
    }

    @Test
    public void testStreamParserInputParsedInstructions() throws InvalidExpressionException {
        String inputStr = "1 + 2 / 3 * 5 - 10";

        InputStream anyInputStream = new ByteArrayInputStream(inputStr.getBytes());

        ParserInput parserInput = new StreamParserInput(anyInputStream);
        Parser parser = new Parser(parserInput);

        LinkedList<Instruction> instructions = new LinkedList<>();
        parser.parseInput(instructions);

        Instruction instruction = instructions.pollFirst();
        Assertions.assertEquals(1.0, instruction.getLeftOperand().getDoubleValue(), 0.0);
        Assertions.assertEquals(2.0, instruction.getRightOperand().getDoubleValue(), 0.0);
        Assertions.assertTrue(instruction instanceof AddInstruction);

        instruction = instructions.pollFirst();
        Assertions.assertEquals(2.0, instruction.getLeftOperand().getDoubleValue(), 0.0);
        Assertions.assertEquals(3.0, instruction.getRightOperand().getDoubleValue(), 0.0);
        Assertions.assertTrue(instruction instanceof DivideInstruction);

        instruction = instructions.pollFirst();
        Assertions.assertEquals(3.0, instruction.getLeftOperand().getDoubleValue(), 0.0);
        Assertions.assertEquals(5.0, instruction.getRightOperand().getDoubleValue(), 0.0);
        Assertions.assertTrue(instruction instanceof MultiplyInstruction);

        instruction = instructions.pollFirst();
        Assertions.assertEquals(5.0, instruction.getLeftOperand().getDoubleValue(), 0.0);
        Assertions.assertEquals(10.0, instruction.getRightOperand().getDoubleValue(), 0.0);
        Assertions.assertTrue(instruction instanceof SubtractInstruction);
    }
}
