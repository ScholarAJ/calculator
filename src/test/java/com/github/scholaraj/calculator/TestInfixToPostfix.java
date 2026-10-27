package com.github.scholaraj.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestInfixToPostfix {

    // Method to test String inputs
    @Test 
    void testInput() {

        /* Tests for expected return values 

           NOTE: replaceAll() and trim() were needed to normalize whitespace. 
           A suitable change might be to normalize input in the class itself. */
           
        assertAll("Input",

            () -> { 
                String postfix = InfixToPostfix.convert("1 + 2 * 3").replaceAll("\\s+", " ").trim();
                assertEquals("1 2 3 * +", postfix);
            },

            () -> { 
                String postfix = InfixToPostfix.convert("(1 + 2) * 3").replaceAll("\\s+", " ").trim();
                assertEquals("1 2 + 3 *", postfix);
            },

            () -> { 
                String postfix = InfixToPostfix.convert("2 ^ 3 ^ 2").replaceAll("\\s+", " ").trim();
                assertEquals("2 3 2 ^ ^", postfix);
            },

            () -> { 
                String postfix = InfixToPostfix.convert("12.5 - 2").replaceAll("\\s+", " ").trim();
                assertEquals("12.5 2 -", postfix);
            },

            () -> { 
                String postfix = InfixToPostfix.convert("-3 2 +").replaceAll("\\s+", " ").trim();
                assertEquals("-3 2 +", postfix);
            }
            
        );

    }

}
