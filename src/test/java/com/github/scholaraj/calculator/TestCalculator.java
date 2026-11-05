package com.github.scholaraj.calculator;

import org.junit.jupiter.api.Test;

import com.github.scholaraj.calculator.core.Calculator;
import com.github.scholaraj.calculator.core.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class TestCalculator {

    Calculator myCalc;

    // Method to create new Calculator instance before each test method
    @BeforeEach 
    void setUp() {
        myCalc = new Calculator();
    }
    

    // Method to test addition output with expressions "1 + 1", "10 + 10", "0 + 0", and "5 + -3"
    @Test 
    void testAddition() {

        assertEquals(2, myCalc.add(1, 1), "1 + 1 should equal 2.");
        assertEquals(20, myCalc.add(10, 10), "10 + 10 should equal 20.");
        assertEquals(0, myCalc.add(0, 0), "0 + 0 should equal 0.");
        assertEquals(2, myCalc.add(5, -3), "5 + -3 should equal 2.");

    }

    // Method to test subtraction with expressions "3 - 2", "6 - 6", "0 - 0", and "3 - 5"
    @Test 
    void testSubtraction() {

        assertEquals(1, myCalc.subtract(3, 2), "3 - 2 should equal 1.");
        assertEquals(0, myCalc.subtract(6, 6), "6 - 6 should equal 0.");
        assertEquals(0, myCalc.subtract(0, 0), "0 - 0 should equal 0.");
        assertEquals(-2, myCalc.subtract(3, 5), "3 - 5 should equal -2.");

    }

    // Method to test multiplication with expressions "0 * 0", "2 * 0", "2 * 2", and "-3 * 4"
    @Test 
    void testMultiplication() {

        assertEquals(0, myCalc.multiply(0, 0), "0 * 0 should equal 0.");
        assertEquals(0, myCalc.multiply(2, 0), "2 * 0 should equal 0.");
        assertEquals(4, myCalc.multiply(2, 2), "2 * 2 should equal 4.");
        assertEquals(-12, myCalc.multiply(-3, 4), "-3 * 4 should equal -12.");

    }

    // Method to test division with expressions "10 / 2", "10 / 5", "5 / 2", "1 / 0", and "0 / 0"
    @Test 
    void testDivision() {

        assertEquals(5, myCalc.divide(10, 2), "10 / 2 should equal 5.");
        assertEquals(2, myCalc.divide(10, 5), "10 / 5 should equal 2.");
        assertEquals(2.5, myCalc.divide(5, 2), "5 / 2 should equal 2.5.");
        assertEquals(Double.POSITIVE_INFINITY, myCalc.divide(1, 0), "1 / 0 should equal positive infinity.");
        assertEquals(Double.NaN, myCalc.divide(0, 0), "0 / 0 should equal NaN.");

    }

    // Method to test modulus with expressions "4 % 2", "5 % 2", "10 % 3", and "-10 % 3"
    @Test 
    void testModulo() {

        assertEquals(0, myCalc.remainder(4, 2), "4 % 2 should equal 0.");
        assertEquals(1, myCalc.remainder(5, 2), "5 % 2 should equal 1.");
        assertEquals(1, myCalc.remainder(10, 3), "10 % 3 should equal 1.");
        assertEquals(-1, myCalc.remainder(-10, 3), "-10 % 3 should equal -1.");
    
    }

    // Method to test exponent with expressions "5 ^ 10", "2 ^ 3", and "2 ^ -2"
    @Test 
    void testExponent() {

        assertEquals(9765625, myCalc.exponent(5, 10));
        assertEquals(8, myCalc.exponent(2, 3));
        assertEquals(0.25, myCalc.exponent(2, -2));

    }

    // Method to test printing of showHist() method
    @Test 
    void testHistory() {

        // Creates new ByteArrayOutputStream object and assigns it to testOutput
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        // Creates a new PrintStream object written to by testOutput, with automatic flushing and UTF-8 encoding
        PrintStream testStream = new PrintStream(testOutput, true, StandardCharsets.UTF_8);

        // Sets the system's standard output to the new testStream object
        System.setOut(testStream);

        myCalc.showHist();

        // Performs a series of tests on showHist() method
        assertAll("History", 
            () -> {

                // Checks the value of String stored in testOutput
                assertTrue(testOutput.toString(StandardCharsets.UTF_8).contains("History is empty!"));

                myCalc.addExp(new Expression("1 + 1", 2));
                myCalc.addExp(new Expression("3 * 4", 12));

                myCalc.showHist();

                // If first assertTrue() succeeded, conduct these other tests
                assertAll("Expression", 
                    () -> assertTrue(testOutput.toString(StandardCharsets.UTF_8).contains("1 + 1")),
                    () -> assertTrue(testOutput.toString(StandardCharsets.UTF_8).contains("3 * 4"))
                );

            }
        );

    }

}
