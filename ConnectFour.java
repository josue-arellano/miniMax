package fourinline2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConnectFour extends Board{
    Board board = new Board();
    public static int boardSize = 8;
    private static Random rand = new Random();
    Scanner input = new Scanner(System.in);

    public ConnectFour() {
        board = new Board();
        board.init();
    }

    public void begin()
    {
        char resp;
        System.out.println("\nDo you want to go first?(y/n)");
        resp = input.nextLine().charAt(0);
        if(resp == 'n'){
            computerMove();
        }
        do{ 
            displayGame();
            System.out.println();
            playerMove();
            computerMove();
            System.out.println();

        }while(true);
    }
    private void playerMove()
    { 
        System.out.println("Enter position on board");
        String resp = input.nextLine();
        char row = resp.charAt(0);
        char col = resp.charAt(1);
        while(!isSquareEmpty(row,col)){
            System.out.print("Tile is already filled\nEnter position on board");
            resp = input.nextLine();
            row = resp.charAt(0);
            col = resp.charAt(1);
        }
        board.stamp('X', row - 'A', Character.getNumericValue(col)-1);
    }
    private boolean isSquareEmpty(char r, char c){
        if (board.at(r - 'A',Character.getNumericValue(c)-1) != '-')
            return false;
        return true;
    }
    private void computerMove()
    {
        Tree stub = new Tree(board);
        Move move = stub.evaluateNextMove();
        board.stamp('O', move.getRow(), move.getCol());
    }
     public void displayGame() 
    {
        board.displayBoard();
    }
}