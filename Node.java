package fourinline2;
import java.util.ArrayList;
import java.util.Random;
class Node {
    private static Random rand = new Random();
        Board board, available_tiles;
        Move new_move;
        ArrayList<Node> children;
        int value;
        Node(Board clone)
        {
            board = clone; 
            children = new ArrayList<>(); 
            available_tiles=clone.clone();
            value = evaluate();
        }
        Board getBoard(){return board;}
        Board getOpenTiles(){return available_tiles;}
        void addChild(Node offspring){children.add(offspring);}
        void removeChild(int index){children.remove(index);}
        void addNewMove(int row, int col) {new_move = new Move(row,col);};
        Move getNewMove(){return new_move;}
        int getValue(){return value;}
        int getNumOfChildren(){return children.size();}
        Node getChild(int index){return children.get(index);}
        private int evaluate() {
            int evaluation = 0;
            for(int i = 0; i < board.boardSize; i++) {
                for(int j = 0; j < board.boardSize - 4; j++) {
                    if(possiblefourHor('O', i, j)) evaluation++;
                    if(possiblefourHor('X', i, j)) evaluation--;
                }
            }

            for(int i = 0; i < board.boardSize; i++) {
                for(int j = 0; j < board.boardSize - 4; j++) {
                    if(possiblefourVert('O', i, j)) evaluation++;
                    if(possiblefourVert('X', i, j)) evaluation--;
                }
            }
            return evaluation;
        }
        private boolean possiblefourHor(char val, int i, int j) {
            for(int k = j; k < j + 4; k++)
                if(board.at(i,k) != val && board.at(i,k) != '-') return false;

            return true;
        }

        private boolean possiblefourVert(char val, int i, int j) {
            for(int k = j; k < j + 4; k++)
                if(board.at(k,i) != val && board.at(k,i) != '-') return false;

                return true;

            }
    }