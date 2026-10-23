package com.github.scholaraj.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class TestCalculator {

    @Test 
    void testAddition() {

        Calculator myCalc = new Calculator();
        assertEquals(2, myCalc.add(1, 1), "1 + 1 should equal 2.");

    }

}
