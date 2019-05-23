package com.chatbot.calculator;

import java.util.Scanner;

public class Calculator {

    public static void calculate() {

        Scanner in = new Scanner(System.in);

        String expression;
        do {
            System.out.print("Type what you want to calculate -> ");
            expression = in.nextLine();
        } while (!isValid(expression));
        expression = " " + expression + " ";
        double result;
        if (expression.contains("(")) {
            result = Double.parseDouble(calculateWithBrackets(expression));
        } else {
            result = Double.parseDouble(calculate(expression));
        }
        System.out.println("The result is : " + result);

    }

    private static boolean countOfBrackets(String expression) {

        boolean bool = true;
        int countOfOpeningBrackets = 0;
        int countOfClosingBrackets = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                countOfOpeningBrackets++;
            }
            if (expression.charAt(i) == ')') {
                countOfClosingBrackets++;
            }
        }
        if (countOfClosingBrackets != countOfOpeningBrackets) {
            bool = false;
        }

        return bool;

    }

    private static boolean isValid(String expression) {

        boolean bool = true;
        if (hasLetter(expression)) {
            bool = false;
        }
        char[] arrayOfSymbols = {'/', '*', '+'};
        for (char arrayOfSymbol : arrayOfSymbols) {
            if (expression.indexOf(arrayOfSymbol) == 0 || expression.indexOf(arrayOfSymbol) == expression.length() - 1 || expression.indexOf('-') == expression.length() - 1) {
                bool = false;
                break;
            }
        }
        if (!countOfBrackets(expression)) {
            bool = false;
        }
        if (!bool) {
            System.out.println("There is something wrong in your expression! \nPlease , type again .");
        }

        return bool;
    }

    private static boolean hasLetter(String expression) {

        expression = expression.replace(" ", "");
        boolean bool = true;
        char[] arrayNumbersAndSymbols = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '/', '*', '-', '+', '.', '(', ')'};
        for (int i = 0; i < expression.length(); i++) {
            bool = true;
            for (char arrayNumberAndSymbol : arrayNumbersAndSymbols) {
                if (expression.charAt(i) == arrayNumberAndSymbol) {
                    bool = false;
                }
            }
            if (bool) {
                break;
            }
        }
        return bool;
    }

    private static String calculateWithBrackets(String expression) {

        while (expression.contains("(")) {
            int indexOfFirstClosingBracket = expression.indexOf(')');
            int indexOfOpeningBracket = 0;
            for (int i = indexOfFirstClosingBracket - 1; i > 0; i--) {
                if (expression.charAt(i) == '(') {
                    indexOfOpeningBracket = i;
                    break;
                }
            }
            String tempExpression = " " + expression.substring(indexOfOpeningBracket + 1, indexOfFirstClosingBracket) + " ";
            String tempString = calculate(tempExpression);
            tempString = tempString.replace(" " , "");
            if (tempString.contains("-")) {
                expression = expression.replace(expression.substring(indexOfOpeningBracket + 1, indexOfFirstClosingBracket), tempString);
            } else {
                expression = expression.replace(expression.substring(indexOfOpeningBracket, indexOfFirstClosingBracket + 1), tempString);
            }
            calculateWithBrackets(expression);
        }
        if (expression.contains("+") || expression.contains("-") || expression.contains("*") || expression.contains("/")) {
            expression = calculate(expression);
        }

        return expression;

    }

    private static String calculate(String expression) {

        while (expression.contains("*") && expression.contains("/")) {
            if (expression.indexOf("*") < expression.indexOf("/")) {
                int i = expression.indexOf("*");
                while (expression.charAt(i) != '/' && expression.charAt(i) != '-' && expression.charAt(i) != '+' && i > 0) {
                    i--;
                }
                int j = expression.indexOf("*") + 1;
                while (expression.charAt(j) != '/' && expression.charAt(j) != '-' && expression.charAt(j) != '+' && expression.charAt(i) != '*' && j < expression.length() - 1) {
                    j++;
                }
                String temp = calculate(expression.substring(i + 1, expression.indexOf("*")), "*", expression.substring(expression.indexOf("*") + 1, j));
                expression = expression.replace(expression.substring(i + 1, j), temp);
            } else {
                int i = expression.indexOf("/");
                while (expression.charAt(i) != '*' && expression.charAt(i) != '-' && expression.charAt(i) != '+' && i > 0) {
                    i--;
                }
                int j = expression.indexOf("/") + 1;
                while (expression.charAt(j) != '*' && expression.charAt(j) != '-' && expression.charAt(j) != '+' && expression.charAt(i) != '*' && j < expression.length() - 1) {
                    j++;
                }
                String temp = calculate(expression.substring(i + 1, expression.indexOf("/")), "/", expression.substring(expression.indexOf("/") + 1, j));
                expression = expression.replace(expression.substring(i + 1, j), temp);
            }
        }

        while (expression.contains("*")) {
            int i = expression.indexOf("*");
            while (expression.charAt(i) != '-' && expression.charAt(i) != '+' && i > 0) {
                i--;
            }
            int j = expression.indexOf("*") + 1;
            while (expression.charAt(j) != '-' && expression.charAt(j) != '+' && expression.charAt(j) != '*' && j < expression.length() - 1) {
                j++;
            }
            String temp = calculate(expression.substring(i + 1, expression.indexOf("*")), "*", expression.substring(expression.indexOf("*") + 1, j));
            expression = expression.replace(expression.substring(i + 1, j), temp);
        }

        while (expression.contains("/")) {
            int i = expression.indexOf("/");
            while (expression.charAt(i) != '-' && expression.charAt(i) != '+' && i > 0) {
                i--;
            }
            int j = expression.indexOf("/") + 1;
            while (expression.charAt(j) != '-' && expression.charAt(j) != '+' && expression.charAt(j) != '/' && j < expression.length() - 1) {
                j++;
            }
            String temp = calculate(expression.substring(i + 1, expression.indexOf("/")), "/", expression.substring(expression.indexOf("/") + 1, j));
            expression = expression.replace(expression.substring(i + 1, j), temp);
        }

        while (expression.contains("+") && expression.contains("-")) {
            if (expression.indexOf("+") < expression.indexOf("-")) {
                int j = expression.indexOf("+") + 1;
                while (expression.charAt(j) != '-' && expression.charAt(j) != '+' && j < expression.length() - 1) {
                    j++;
                }
                String temp = calculate(expression.substring(1, expression.indexOf("+")), "+", expression.substring(expression.indexOf("+") + 1, j));
                expression = expression.replace(expression.substring(1, j), temp);
            } else {
                if (expression.indexOf("-") == 1) {
                    int j = expression.indexOf("-") + 1;
                    while (expression.charAt(j) != '+' && expression.charAt(j) != '-' && j < expression.length() - 1) {
                        j++;
                    }
                    int tempIndex = j;
                    j = tempIndex + 1;
                    while (expression.charAt(j) != '+' && expression.charAt(j) != '-' && expression.charAt(j) != ' ' && j < expression.length() - 1) {
                        j++;
                    }
                    if (expression.charAt(tempIndex) == '-') {
                        String temp = calculate(expression.substring(1, tempIndex), "-", expression.substring(tempIndex + 1, j));
                        expression = expression.replace(expression.substring(1, j), temp);
                    } else if (expression.charAt(tempIndex) == '+') {
                        String temp = calculate(expression.substring(1, tempIndex), "+", expression.substring(tempIndex + 1, j));
                        expression = expression.replace(expression.substring(1, j), temp);
                    }
                } else {
                    int j = expression.indexOf("-") + 1;
                    while (expression.charAt(j) != '+' && expression.charAt(j) != '-' && j < expression.length() - 1) {
                        j++;
                    }
                    String temp = calculate(expression.substring(1, expression.indexOf("-")), "-", expression.substring(expression.indexOf("-") + 1, j));
                    expression = expression.replace(expression.substring(1, j), temp);
                }
            }
        }

        while (expression.contains("+")) {
            int j = expression.indexOf("+") + 1;
            while (expression.charAt(j) != '+' && j < expression.length() - 1) {
                j++;
            }
            String temp = calculate(expression.substring(1, expression.indexOf("+")), "+", expression.substring(expression.indexOf("+") + 1, j));
            expression = expression.replace(expression.substring(1, j), temp);
        }

        while (expression.contains("-")) {
            int countOfMinuses = 0;
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) == '-') {
                    countOfMinuses++;
                }
            }
            if (countOfMinuses == 1 && expression.indexOf("-") == 1) {
                break;
            } else {
                if (expression.indexOf("-") == 1) {
                    int j = expression.indexOf("-") + 1;
                    while (expression.charAt(j) != '+' && expression.charAt(j) != '-' && j < expression.length() - 1) {
                        j++;
                    }
                    int tempIndex = j;
                    j = tempIndex + 1;
                    while (expression.charAt(j) != '+' && expression.charAt(j) != '-' && expression.charAt(j) != ' ' && j < expression.length() - 1) {
                        j++;
                    }
                    if (expression.charAt(tempIndex) == '-') {
                        String temp = calculate(expression.substring(1, tempIndex), "-", expression.substring(tempIndex + 1, j));
                        expression = expression.replace(expression.substring(1, j), temp);
                    }
                } else {
                    int j = expression.indexOf("-") + 1;
                    while (expression.charAt(j) != '-' && j < expression.length() - 1) {
                        j++;
                    }
                    String temp = calculate(expression.substring(1, expression.indexOf("-")), "-", expression.substring(expression.indexOf("-") + 1, j));
                    expression = expression.replace(expression.substring(1, j), temp);
                }
            }
        }
        return expression;

    }


    private static String calculate(String numOne, String operator, String numTwo) {

        double numberOne = Double.parseDouble(numOne);
        double numberTwo = Double.parseDouble(numTwo);
        double temp = 0;
        switch (operator) {
            case "/":
                temp = numberOne / numberTwo;
                break;
            case "*":
                temp = numberOne * numberTwo;
                break;
            case "+":
                temp = numberOne + numberTwo;
                break;
            case "-":
                temp = numberOne - numberTwo;
                break;
        }

        return String.valueOf(temp);
    }

}
