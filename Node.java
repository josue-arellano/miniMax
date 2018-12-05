package fourinline2;
import java.util.ArrayList;
import java.util.Random;
class Node {
    private static Random rand = new Random();
        Board board, available_tiles;
        ArrayList<Node> children;
        int value = rand.nextInt(100);
        Node(Board clone)
        {
            board = clone; 
            children = new ArrayList<>(); 
            available_tiles=clone.clone();
        }
        Board getBoard(){return board;}
        Board getOpenTiles(){return available_tiles;}
        void addChild(Node offspring){children.add(offspring);}
        void removeChild(int index){children.remove(index);}
        int getValue(){return value;}
        int getNumOfChildren(){return children.size();}
        Node getChild(int index){return children.get(index);}
        void evaluate(){;}
    }