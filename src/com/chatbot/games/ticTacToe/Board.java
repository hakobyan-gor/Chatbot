package com.chatbot.games.ticTacToe;

class Board {

    private Character[][] board = null;
    private int size;

    void setSizeOfBoard(int size) {
        this.size = size;
    }

    void initializeTheBoard() {

        board = new Character[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }

    }

    void initializeTheBoard(Character[][] board) {

        this.board = board;

    }

    void printTheBoard() {

        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + "  ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }

    }

    boolean isFieldFull(Character[][] board) {

        boolean bool = true;
        for (Character[] rows : board) {
            for (Character columns : rows) {
                if (columns == '-') {
                    bool = false;
                }
            }
        }

        return bool;
    }

    public int getSize() {
        return size;
    }

    public Character[][] getBoard() {
        return board;

    }

    public boolean checkPlaseOfMarks(int row, int column) {

        if (row >= 0 && row < size) {
            if (column >= 0 && column < size) {
                if (board[row][column] == '-') {
                    board[row][column] = ']';
                } else {
                    System.out.println("This square is taken !");
                    System.out.println("Please try again !");

                }
            } else {
                System.out.println("You are out of the field boundaries.\nPlease try again !");

            }
        } else {
            System.out.println("You are out of the field boundaries.\nPlease try again !");

        }

        return false;
    }

    public boolean checkForWin() {

        return checkWinHorizontally() ||
                checkWinVertically() ||
                checkWinDiagonally() ||
                checkWinBackDiagonally();
    }

    private boolean checkWinVertically() {

        return false;
    }

    private boolean checkWinHorizontally() {

        return false;
    }

    private boolean checkWinDiagonally() {

        return false;
    }

    private boolean checkWinBackDiagonally() {

        return false;
    }

}
