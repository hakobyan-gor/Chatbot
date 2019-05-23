package com.chatbot.games.ticTacToe;

public class TicTacToe {

    private UserInput userInput = new UserInput();
    private Board board = new Board();

    public void startTheGame(){
        board.setSizeOfBoard(userInput.inputSizeOfTheBoard());

    }

}
