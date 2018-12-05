package fourinline2;

import java.util.ArrayList;
import java.util.Random;

public class Board {
     char[][] board;
    public static final int boardSize = 8;
    private static Random rand = new Random();

    public Board() {
        board = new char[boardSize][boardSize];
        init();
    }

    public Board(Board b) {
        this();
        copy(b.board);
    }

    public void init() {
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                board[i][j] = '-';
            }
        }
    }
    private void copy(char[][] board) {
        for (int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }
    public Board clone() {
        //char[][] copy = new char[boardSize][boardSize];
        return new Board(this);
    }
    public char at(int row, int col){
        return board[row][col];
    }
    public void stamp(char symbol, int row, int col)
    {
        board[row][col] = symbol;
    }
    public void displayBoard() {
        System.out.println("  1 2 3 4 5 6 7 8\tPlayer vs. Opponent");
        for(int i = 0; i < boardSize; i++) {
            char row = (char) ('A' + i);
            System.out.print(row);
            for(int j = 0; j < boardSize; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }
    public int getSize()
    {
        return boardSize;
    }
    public boolean isFull()
    {
        for(int i = 0; i < boardSize; i++)
            for(int j = 0; j < boardSize; j++)
                if(board[i][j] == '-')
                    return false;
        return true;
    }
}