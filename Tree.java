package fourinline2;
import java.util.ArrayList;
import java.util.Random;
public class Tree {

    class Node {
        Board bnode;
        ArrayList<Node> children;
        int value = 5;
        Node(Board clone){bnode = clone; children = new ArrayList<>();}
        Board getBoard(){return bnode;}
        void addChild(Node offspring){children.add(offspring);}
        int getValue(){return value;}
        int getNumOfChildren(){return children.size();}
        Node getChild(int index){return children.get(index);}
        void evaluate(){;}
    }
     Node root;
     Board emptyTiles;
     private static Random rand = new Random();

     Tree (Board rootBoard)
     {
        emptyTiles = new Board(rootBoard.clone());
        root = new Node(rootBoard.clone());
     }  
    int evaluateNextMove(){
        return miniMax(root, true, 0, (int) Double.NEGATIVE_INFINITY, (int)Double.POSITIVE_INFINITY);
    }
    int miniMax(Node root, boolean isMax, int depth, int alpha, int beta)
    {
        int bestValue, generation_limit = 10;
        System.out.println("Depth: " + depth);
        if(depth == 4){
            root.evaluate();
            return root.getValue();
        }
        if(isMax)
        {
            bestValue = (int) Double.NEGATIVE_INFINITY;
            for (int child=0; child < generation_limit ;child++) 
                {
                    Node newChild = generateChild(root, emptyTiles);
                    root.addChild(newChild);
                    int value = miniMax(root.getChild(child), false, depth+1, alpha, beta);
                    bestValue = Math.max(bestValue, value);
                    alpha = Math.max(alpha, bestValue);
                    if (beta <= alpha) break;
                
                }
            return bestValue;
        }
        else
        {
            bestValue = (int) Double.POSITIVE_INFINITY;
            for (int child=0; child< generation_limit ;child++) 
                {
                    Node newChild = generateChild(root, emptyTiles);
                    root.addChild(newChild);
                    int value = miniMax(root.getChild(child), true, depth+1, alpha, beta);
                    bestValue = Math.min(bestValue, value);
                    beta = Math.min(beta, bestValue);
                    if (beta <= alpha) break;
                }
            return bestValue;
        }
    }
    Node generateChild(Node parent, Board openTiles)
    {
        Board starterBoard = root.getBoard();
        Board temp = new Board(starterBoard); // generate board copy
        int gen_size = 0, gen_size_limit = 25, row, col;
        do{row =rand.nextInt(7); col = rand.nextInt(7);
        }while(!isSquareEmpty(openTiles,row,col)); // check if move is new

        System.out.print("Found a new move");
        temp.stamp('0', row, col); // add new move to board copy
        openTiles.stamp('0',row,col); // remove one empty tile from avail_tiles
        return new Node(temp);
    }
    private boolean isSquareEmpty(Board target, int r, int c){
        if (target.at(r,c) != '-')
            return false;
        return true;
    }

}