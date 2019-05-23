package com.chatbot;

import com.chatbot.calculator.Calculator;
import com.chatbot.games.GuessNumber;
import com.chatbot.repository.Repository;

import java.util.Scanner;

class App {

    private Scanner scanner = new Scanner(System.in);
    private Repository repository = new Repository();
    private GuessNumber guessNumber = new GuessNumber();

    void start() {

        String input = "";
        System.out.println("Let's start.\nYou can ask me questions.\n" +
                "Or we can talk\n" +
                "Or you can use the following games :\n" +
                "TicTacToe\n" +
                "Guess Number\n" +
                "Or Calculator\n" +
                "Just write what you want to use\n" +
                "If you want to sing out , write \"Exit\".");
        System.out.print("What is your name ? ");
        String user = scanner.nextLine();
        while (!input.toLowerCase().equals("exit")) {
            System.out.print(user + " : ");
            input = scanner.nextLine();
            if (input.toLowerCase().equals("calculator")) {
                Calculator.calculate();
            } /*else if (input.toLowerCase().equals("ticTacToe")) {

            }*/ else if (input.toLowerCase().equals("guess number")) {
                System.out.println("Guess a number between 1 and 500 : ");
                int number = scanner.nextInt();
                while (!guessNumber.guessNumber(number)) {
                    number = scanner.nextInt();
                }
            } else if (repository.getAnswer(input).equals("I can not answer your question!\nPlease, ask another question.")) {
                System.out.print("Me : \nI can not answer your question .\n");
                System.out.println("Please, teach me !\nQuestion : ");
                String question = scanner.nextLine();
                System.out.println("Answer : ");
                String answer = scanner.nextLine();
                repository.setQuestionsAndAnswers(question, answer);
            } else {
                System.out.println("Me : " + repository.getAnswer(input));
            }
        }

    }

}
