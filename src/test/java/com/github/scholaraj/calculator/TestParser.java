package com.github.scholaraj.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestParser {

    @Test 
    void testInput() {

        InputParser parser = new InputParser();

        assertAll("Input", () -> {

            assertAll("Valid",

                () -> {
                    parser.readNew("1 + 2 * 3");
                    assertTrue(parser.isValid());
                },

                () -> {
                    parser.readNew("(1 + 2) * 3");
                    assertTrue(parser.isValid());
                },

                () -> {
                    parser.readNew("-4.5");
                    assertTrue(parser.isValid());
                },

                () -> {
                    parser.readNew("2 ^ 3 ^ 2");
                    assertTrue(parser.isValid());
                },

                () -> {
                    parser.readNew("1 + (2 * 3)");
                    assertTrue(parser.isValid());
                }

            );

            assertAll("Invalid", 

                () -> {
                    parser.readNew("1 +");
                    assertFalse(parser.isValid());
                },

                () -> {
                    parser.readNew("1 ** 2");
                    assertFalse(parser.isValid());
                },

                () -> {
                    parser.readNew("(1 + 2");
                    assertFalse(parser.isValid());
                },

                () -> {
                    parser.readNew("1 2");
                    assertFalse(parser.isValid());
                },

                () -> {
                    parser.readNew("3..4");
                    assertFalse(parser.isValid());
                }

            );

        });

    }

}
