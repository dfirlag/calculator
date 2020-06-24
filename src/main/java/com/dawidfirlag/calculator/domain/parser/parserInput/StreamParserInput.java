package com.dawidfirlag.calculator.domain.parser.parserInput;

import com.dawidfirlag.calculator.domain.parser.ParserInput;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StreamParserInput implements ParserInput {

    private final InputStream in;

    public StreamParserInput(InputStream in) {
        this.in = in;
    }

    @Override
    public String getInputString() {
        try (Scanner scanner = new Scanner(this.in)) {
            scanner.useDelimiter(Pattern.compile("[;|\\r?\\n|\\r]"));
            return scanner.hasNext() ? scanner.next() : null;
        }
    }
}
