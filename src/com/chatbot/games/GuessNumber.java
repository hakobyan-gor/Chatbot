package com.chatbot.games;

import java.util.Random;

public class GuessNumber {

    private Random random = new Random();
    private boolean win = false;
    private int guess = random.nextInt(500);
    private int counter = 0;

    public boolean guessNumber(int number) {
        if (guess == number) {
            win = true;
            System.out.printf("You win!\nCount of steps that you passed to guess the number : %d\n", counter);
            return true;
        } else if (guess < number) {
            System.out.println("Try again ! Note : Type smaller !");
            counter++;
            return false;
        } else {
            System.out.println("Try again ! Note : Type bigger !");
            counter++;
            return false;
        }

    }


}
