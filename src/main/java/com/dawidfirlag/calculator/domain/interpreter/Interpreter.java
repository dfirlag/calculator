package com.dawidfirlag.calculator.domain.interpreter;

import com.dawidfirlag.calculator.domain.interpreter.dto.ResultDto;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.interpreter.operand.BracketExpressionOperand;
import com.dawidfirlag.calculator.domain.interpreter.operand.ValueOperand;

import java.util.*;

public class Interpreter {
    
    private final LinkedList<Instruction> instructions;
    private double result;

    public Interpreter() {
        this.instructions = new LinkedList<>();
    }

    public void calculate() throws DividedByZeroException {
        this.result = this.calculateInstructions(this.instructions);
    }

    private double calculateInstructions(LinkedList<Instruction> instructions) throws DividedByZeroException {
        if (instructions.isEmpty()) {
            return 0;
        }

        for (OperatorsPriority priority : OperatorsPriority.values()) {
            ListIterator<Instruction> it = instructions.listIterator();

            if (instructions.size() <= 1) {
                break;
            }

            while (it.hasNext()) {
                Instruction instruction = it.next();

                if (instruction.getPriorityOperator() != priority) {
                    continue;
                }

                if (instructions.size() <= 1) {
                    break;
                }

                this.executeInstruction(instruction, it);
            }
        }

        return this.getCalculatedInstructionResult(instructions.getLast());
    }

    private void executeInstruction(Instruction instruction, ListIterator<Instruction> it)
        throws DividedByZeroException
    {
        double instructionResult = this.getCalculatedInstructionResult(instruction);
        it.remove();

        if (it.hasPrevious()) {
            Instruction previousInstruction = it.previous();
            previousInstruction.updateRightOperand(new ValueOperand(instructionResult));
            it.next();
        }

        if (it.hasNext()) {
            Instruction nextInstruction = it.next();
            nextInstruction.updateLeftOperand(new ValueOperand(instructionResult));
            it.previous();
        }
    }

    private double getCalculatedInstructionResult(Instruction instruction) throws DividedByZeroException {
        if (instruction.getLeftOperand() instanceof BracketExpressionOperand) {
            instruction.updateLeftOperand(
                    this.getResolvedExpressionOperand((BracketExpressionOperand) instruction.getLeftOperand())
            );
        }

        if (instruction.getRightOperand() instanceof BracketExpressionOperand) {
            instruction.updateRightOperand(
                    this.getResolvedExpressionOperand((BracketExpressionOperand) instruction.getRightOperand())
            );
        }

        return instruction.calculate();
    }

    private Operand getResolvedExpressionOperand(BracketExpressionOperand bracketExpressionOperand) throws DividedByZeroException {
        return new ValueOperand(this.calculateInstructions(bracketExpressionOperand.getInstructionsList()));
    }

    public LinkedList<Instruction> getInstructionsList() {
        return instructions;
    }

    public ResultDto getResult() {
        return new ResultDto(this.result);
    }
}
