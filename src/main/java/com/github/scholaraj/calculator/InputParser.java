package com.github.scholaraj.calculator;

// Class for parsing and validating expressions 

/* 

Grammar rules are as follows:

Expression -> Term (("+" | "-") Term)*
Term       -> Factor (("*" | "/" | "%") Factor)*
Factor     -> "-" Factor | Power
Power      -> Primary ["^" Factor]
Primary    -> Number | "(" Expression ")"

*/

public class InputParser {

    // String given by user
    private String input;

    // Current index in input
    private int position;

    public InputParser(String userInput) {
        input  = userInput;
        position = 0;
    }
    
    // Method to check valid input
    public boolean isValid() {

        if (input.isEmpty()) return false;

        if (!parseExpression()) return false;
        
        skipWhitespace();
        return atEnd();
        
    }

    // Method to skip past whitespace
    private void skipWhitespace() {

        while (!(atEnd())) {

            if (!(input.isEmpty()) && Character.isWhitespace(input.charAt(position))) {
                position++;
            }

            else {
                break;
            }

        }
        

    }

    // Method to check for end of String
    private boolean atEnd() {
        return position >= input.length();
    }

    // Returns character at current index
    private char currentChar() {
        return input.charAt(position);
    }

    // Method to parse through expression
    private boolean parseExpression() {
        
        // If parseTerm() returns false, return false for entire expression
        if (!parseTerm()) return false;

        skipWhitespace();

        // If end of String is reached without dangling operators, return true
        if (atEnd() || (currentChar() != '+' && currentChar() != '-')) {
            return true;
        }

        // Consumes + or - operator
        position++; 

        // Parses second term
        return parseExpression(); 
        
    }

    // Method to parse through term
    private boolean parseTerm() {
        
        // If parseFactor() returns false, return false for entire expression
        if (!parseFactor()) return false;

        skipWhitespace();

        // If end of String is reached without dangling operators, return true
        if (atEnd() || (currentChar() != '*' && currentChar() != '/' && currentChar() != '%')) {
            return true;
        }

        // Consumes * or / operator
        position++; 

        // Parses second term
        return parseTerm(); 
        
    }

    // Method to parse through factor
    private boolean parseFactor() {
        
        skipWhitespace();

        if (atEnd()) return false;

        char current = currentChar();

        // If sign precedes factor, increment position
        if (current == '-') {

            // Consumes unary sign
            position++;

            // Checks that sign is followed by a factor
            return parseFactor();   
        }

        return parsePower(); 

    }

    // Method to parse through exponent
    private boolean parsePower() {

        // If parsePrimary returns false, return false for entire expression
        if (!parsePrimary()) return false;

        skipWhitespace();

        // If not at end of String and current character is ^, parse through next factor 
        if (!atEnd() && currentChar() == '^') {

            // Consumes ^
            position++; 

            return parseFactor();
        }

        // If no factor is included, return true
        return true;

    }

    // Method to parse through a number or expression in parentheses
    private boolean parsePrimary() {

        skipWhitespace();

        if (atEnd()) return false;

        char current = currentChar();

        // If left parenthesis precedes expression, increment position
        if (current == '(') {

            // Consumes ( 
            position++;
            
            // If no expression follows left parenthesis, return false
            if (!parseExpression()) return false;

            skipWhitespace();

            // If there is no closing right parenthesis, return false
            if (atEnd() || currentChar() != ')') return false;

            // Consumes )
            position++;

            return true;
            
        }

        return parseNumber();

    }

    // Method to parse through number itself (integer or decimal)
    private boolean parseNumber() {

        char current = currentChar();

        // Boolean variables to check number format
        boolean hasDigit = false;
        boolean hasDecimal = false;

        // Iterate through number while index is in bounds
        while (!(atEnd())) {

            // If current character is a digit, set hasDigit to true and increment index
            if (Character.isDigit(current)) {

                hasDigit = true;
                position++;

            }
 
            // If current character is decimal point, set hasDecimal true and increment index
            else if (current == '.') {

                // If current number already has decimal point, return false
                if (hasDecimal) return false;

                hasDecimal = true;
                position++; 

            }

            else {
                break;
            }

            if (!atEnd()) current = currentChar();

        }

        return hasDigit;

    }
    
}
