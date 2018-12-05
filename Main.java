package fourinline2;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        ConnectFour game = new ConnectFour();
        game.begin();
       }

    public static void clearBoard() throws IOException{ 
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}