package com.dawidfirlag.calculator.domain.parser;

import com.dawidfirlag.calculator.domain.interpreter.Instruction;
import com.dawidfirlag.calculator.domain.interpreter.Operand;
import com.dawidfirlag.calculator.domain.interpreter.Operators;
import com.dawidfirlag.calculator.domain.interpreter.exception.InvalidInstructionException;
import com.dawidfirlag.calculator.domain.interpreter.factory.InstructionFactory;
import com.dawidfirlag.calculator.domain.interpreter.operand.BracketExpressionOperand;
import com.dawidfirlag.calculator.domain.interpreter.operand.ValueOperand;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {
    private final static int MSG_SCANNER_EXPRESSION_NUM = 3;

    private final InstructionFactory instructionFactory;
    private final Pattern operatorsPattern;
    private String lastScannerValue;
    private final ParserInput parserInput;

    public Parser(ParserInput parserInput) {
        this.instructionFactory = new InstructionFactory();
        this.operatorsPattern = Operators.getAvailableOperatorsPattern();
        this.parserInput = parserInput;
    }

    public void parseInput(
        LinkedList<Instruction> instructions
    ) throws InvalidExpressionException {
        this.parseStringInput(parserInput.getInputString(), instructions);
    }

    protected void parseStringInput(
        String inputString,
        LinkedList<Instruction> instructions
    ) throws InvalidExpressionException {
        inputString = this.prepareStringToParse(inputString);
        if (inputString.isEmpty()) {
            throw new InvalidExpressionException("Expression in empty");
        }

        Operand leftOperand;
        Operand rightOperand;
        Operators operator;

        Scanner scanner = new Scanner(inputString);
        try {
            leftOperand = this.getNextOperand(scanner);
            if (!scanner.hasNext()) {
                throw new InvalidExpressionException("Missing operator");
            }

            while (scanner.hasNextLine()) {
                operator = this.getNextOperator(scanner);
                rightOperand = this.getNextOperand(scanner);

                Instruction instruction = this.instructionFactory.createInstructionByString(
                    operator,
                    leftOperand,
                    rightOperand
                );

                instructions.add(instruction);
                leftOperand = rightOperand;
            }
        } catch (InvalidInstructionException e) {
            throw new InvalidExpressionException("Invalid instruction: " + this.getMsgScannerExpression(scanner));
        } finally {
            scanner.close();
        }
    }

    private Operand getNextOperand(Scanner scanner) throws InvalidExpressionException {
        if (scanner.hasNextDouble()) {
            return new ValueOperand(scanner.nextDouble());
        } else if (scanner.hasNext("[(]")) {
            return getBracketExpressionOperand(scanner);
        } else if (scanner.hasNext("[-]")) {
            scanner.next();
            Operand operand = this.getNextOperand(scanner);
            operand.setAsNegativeOperand();
            return operand;
        }

        throw new InvalidExpressionException("Invalid expression: " + this.getMsgScannerExpression(scanner));
    }

    private Operators getNextOperator(Scanner scanner) throws InvalidExpressionException {
        if (scanner.hasNext(this.operatorsPattern)) {
            return Operators.getByValue(this.getNextScannerValue(scanner).charAt(0));
        } else {
            throw new InvalidExpressionException("Excepted operator, but got: " + this.getMsgScannerExpression(scanner));
        }
    }

    private String prepareStringToParse(String inputString) {
        inputString = inputString.replaceAll("\\s", "");
        inputString = inputString.replaceAll("([\\d]+|[+-/\\(\\)\\*]{1})", "$1 ").trim();
        return inputString;
    }

    private Operand getBracketExpressionOperand(Scanner scanner) throws InvalidExpressionException {
        this.getNextScannerValue(scanner);
        StringBuilder bracketExpression = new StringBuilder();
        int openBracketCounter = 0;
        while (!(scanner.hasNext("[)]") && openBracketCounter == 0)) {
            String next = this.getNextScannerValue(scanner);
            if (next.equals("(")) {
                openBracketCounter++;
            } else if (next.equals(")")) {
                openBracketCounter--;
            }
            bracketExpression.append(next).append(" ");
        }

        BracketExpressionOperand expressionOperand = new BracketExpressionOperand();
        this.parseStringInput(bracketExpression.toString().trim(), expressionOperand.getInstructionsList());
        bracketExpression.setLength(0);
        this.getNextScannerValue(scanner);
        return expressionOperand;
    }

    private String getMsgScannerExpression(Scanner scanner) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.lastScannerValue);

        for (int i = 0; i < MSG_SCANNER_EXPRESSION_NUM; i++) {
            if (!scanner.hasNext()) {
                break;
            }

            stringBuilder.append(scanner.next());
        }

        return stringBuilder.toString();
    }

    private String getNextScannerValue(Scanner scanner) {
        String next = scanner.next();
        lastScannerValue = next;
        return next;
    }
}
