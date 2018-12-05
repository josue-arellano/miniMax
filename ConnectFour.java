package fourinline2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConnectFour extends Board{
    class Coord {
        int row, col;
        Coord(int r, int c){row = r; col= c;}
        void set(int r, int c){row = r; col= c;}
        int getRow(){return row;}
        int getCol(){return col;}
    }
    Board board = new Board();
    public static int boardSize = 8;
    private static Random rand = new Random();
    Scanner input = new Scanner(System.in);
    /*private class Player {
        private char symbol = '';
        Player(char x_or_o){symbol = x_or_o}
        public setStamp(){symbol = x_or_o}
        public stamp(){return symbol;}

    } */

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
            playerMove();
            displayGame();
            computerMove();

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
        stub.evaluateNextMove();
    }
    // private  ArrayList<Coord> longestChain(Board board, char symbol){
    //     //check horizontal
    //     ArrayList<Coord> longestChain = new ArrayList<>();
    //     ArrayList<Coord> tempChain = new ArrayList<>();
    //     for (int i = 0; i < boardSize; i++)
    //         for (int j = 0; j < boardSize; j++){
    //             if(longestChain.size() == 4)
    //                 return longestChain; 
    //             else if(board.at(i,j) == symbol)
    //                 tempChain.add(new Coord(i,j));
    //             else
    //             {
    //                 if(tempChain.size() > longestChain.size())
    //                     longestChain = new ArrayList<Coord>(tempChain);
    //                 tempChain.clear();
    //             }
    //     }
    //     //check vertical
    //     for (int j = 0; j < boardSize; j++)
    //         for (int i = 0; i < boardSize; i++){
    //             if(longestChain.size() == 4)
    //                 return longestChain; 
    //             else if(board.at(i,j) == symbol)
    //                 tempChain.add(new Coord(i,j));
    //             else
    //             {
    //                 if(tempChain.size() > longestChain.size())
    //                     longestChain = new ArrayList<Coord>(tempChain);
    //                 tempChain.clear();
    //             }
    //     }
    //     return longestChain;
    // }


     public void displayGame() 
    {
        board.displayBoard();
    }
}