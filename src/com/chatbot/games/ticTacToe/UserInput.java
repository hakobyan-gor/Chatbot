package com.chatbot.games.ticTacToe;

import java.util.Scanner;

class UserInput {

    private Scanner scanner = new Scanner(System.in);
    private Board board = new Board();

    int inputSizeOfTheBoard() {

        System.out.print("Input size of the board(e.g 3x3 , 5x5 etc) : ");

        return scanner.nextInt();
    }

    int[] placeMarks() {

        System.out.print("Please , select a row !");
        int row = scanner.nextInt() - 1;
        System.out.print("Please , select a column !");
        int column = scanner.nextInt() - 1;

        int[] location = {row,column};

        return location;
    }

}
