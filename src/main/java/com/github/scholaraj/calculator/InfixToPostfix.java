package com.github.scholaraj.calculator;

import java.util.ArrayDeque;

public final class InfixToPostfix {

    public static int getPrecedence(char operator) {

        switch (operator) {
            case '^' : 
                return 3;
                
            case '*' :
                return 2;

            case '/' : 
                return 2;

            case '+' :
                return 1;
            
            case '-' :
                return 1;

            default:
                return 0;
        }
        
    } 

    public static boolean rightAssociative(char operator) {
        if (operator == '^') {
            return true;
        }

        else {
            return false;
        }
    }

    public static String convert(String userInput) {



    }

}
