package com.dawidfirlag.calculator.domain.parser.parserInput;

import com.dawidfirlag.calculator.domain.parser.ParserInput;

public class StringParserInput implements ParserInput {

    private final String input;

    public StringParserInput(String input) {
        this.input = input;
    }

    @Override
    public String getInputString() {
        return this.input;
    }
}
