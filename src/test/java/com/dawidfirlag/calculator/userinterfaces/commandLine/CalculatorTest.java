package com.dawidfirlag.calculator.userinterfaces.commandLine;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    @ExpectSystemExitWithStatus(1)
    public void testMainWithCorrectExit() {
        String[] args = {"1 + 4", "/ 2"};
        Calculator.main(args);
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    public void testMainWithIncorrectExit() {
        String[] args = {"1 + + 4", "/ 2"};
        Calculator.main(args);
    }
}
