package com.github.scholaraj.calculator.core;

import java.util.ArrayDeque;

public final class InfixToPostfix {

    // Method to find precedence of operator
    private static int getPrecedence(char operator) {

        // Switch statement to evaluate operator precedence
        switch (operator) {
            case '^' : 
                return 3;
                
            case '*' :
                return 2;

            case '/' : 
                return 2;

            case '%' : 
                return 2;    

            case '+' :
                return 1;
            
            case '-' :
                return 1;

            default:
                return 0;
        }
        
    } 

    // Method to check whether operator is right associative
    private static boolean rightAssociative(char operator) {

        // If operator is of exponent, return true 
        if (operator == '^') {
            return true;
        }

        // Otherwise return false
        else {
            return false;
        }

    }

    // Method to convert infix expression to postfix
    public static String convert(String infix) {

        // Stack object for storing operators 
        ArrayDeque<Character> opStack = new ArrayDeque<Character>();

        // String object for storing expression in postfix
        String postfix = "";

        // For loop to iterate through user-provided String
        for (int i = 0; i < infix.length(); i++) {

            char curr = infix.charAt(i);

            // If current character is whitespace, skip
            if (Character.isWhitespace(curr)) {
                continue;
            }

            // If minus sign appears next to number, count it as unary operator
            else if (curr == '-' && (i + 1) < infix.length() && infix.charAt(i + 1) != ' ') {
                postfix += curr;
            }

            // If decimal point appears next to number, append it normally
            else if (curr == '.' && (i + 1) < infix.length() && infix.charAt(i + 1) != ' ') {
                postfix += curr;
            }

            // If current character is digit, add it to postfix
            else if (Character.isDigit(curr)) {
                postfix += curr;

                // If next char is an operator or whitespace, add space in postfix
                if ((i + 1) < infix.length()) {

                    char nextChar = infix.charAt(i + 1);

                    switch (nextChar) {
                        case ' ', '*', '/', '%', '+', '-':
                            postfix += " ";

                        default:
                            break;
                    }

                }
                
            }

            // If the current character is a left parenthesis, add it to stack
            else if (curr == '(') {
                opStack.push(curr);
            }

            // If current character is right parenthesis, iterate through stack until left parenthesis
            else if (curr == ')') {

                postfix += " ";

                // Add operators from stack to postfix and remove them
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    postfix += opStack.pop();
                    postfix += " ";
                }

                // Remove left parenthesis from stack
                opStack.pop();
            }

            else {

                // Add operators from stack to postfix and remove them                
                while (!opStack.isEmpty() && opStack.peek() != '(' && (getPrecedence(opStack.peek()) > getPrecedence(curr) || (getPrecedence(opStack.peek()) == getPrecedence(curr) && !rightAssociative(curr)))) {
                    
                    /*  If current character has precedence greater than top element of stack
                    or equal without right associativity, add to postfix. */
                    postfix += opStack.pop();
                    postfix += " ";

                }

                // Push final operator to stack
                opStack.push(curr);
            }

        }

        // Pop left over operators
        while (!(opStack.isEmpty())) {
            postfix += " ";
            postfix += opStack.pop();
            postfix += " ";
        }

        // Normalizes whitespace (if needed)
        postfix = postfix.replaceAll("\\s+", " ").trim();

        return postfix;

    }

}
