package com.dawidfirlag.calculator.application;

import com.dawidfirlag.calculator.domain.interpreter.Interpreter;
import com.dawidfirlag.calculator.domain.interpreter.dto.ResultDto;
import com.dawidfirlag.calculator.domain.interpreter.exception.DividedByZeroException;
import com.dawidfirlag.calculator.domain.parser.Parser;
import com.dawidfirlag.calculator.domain.parser.ParserInput;
import com.dawidfirlag.calculator.domain.parser.exceptions.InvalidExpressionException;
import com.dawidfirlag.calculator.domain.parser.parserInput.StreamParserInput;
import com.dawidfirlag.calculator.domain.parser.parserInput.StringParserInput;
import com.dawidfirlag.calculator.infrastructure.model.Expression;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class CalculatorQueryService {

    public ResultDto calculateExpressionFromStringArray(String[] arg)
        throws InvalidExpressionException, DividedByZeroException
    {
        String inputStr = String.join(" ", arg);
        return this.calculateExpressionFromString(inputStr);
    }

    public ResultDto calculateExpressionFromString(String inputStr)
            throws InvalidExpressionException, DividedByZeroException
    {
        Interpreter interpreter = new Interpreter();
        ParserInput parserInput = new StringParserInput(inputStr);
        Parser parser = new Parser(parserInput);

        parser.parseInput(interpreter.getInstructionsList());
        interpreter.calculate();
        return interpreter.getResult();
    }

    public ResultDto calculateExpressionFromStream(InputStream inputStream)
            throws InvalidExpressionException, DividedByZeroException
    {
        Interpreter interpreter = new Interpreter();
        StreamParserInput parserInput = new StreamParserInput(inputStream);
        Parser parser = new Parser(parserInput);
        parser.parseInput(interpreter.getInstructionsList());
        interpreter.calculate();
        return interpreter.getResult();
    }
}
