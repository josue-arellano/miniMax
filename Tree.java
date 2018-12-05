package fourinline2;
import java.util.ArrayList;
import java.util.Random;
public class Tree {
    private static Random rand = new Random();
    class Node {
        Board bnode, emptyTiles;
        ArrayList<Node> children;
        int value = rand.nextInt(100);
        Node(Board clone){bnode = clone; children = new ArrayList<>(); emptyTiles=clone.clone();}
        Board getBoard(){return bnode;}
        Board getOpenTiles(){return emptyTiles;}
        void addChild(Node offspring){children.add(offspring);}
        void removeChild(int index){children.remove(index);}
        int getValue(){return value;}
        int getNumOfChildren(){return children.size();}
        Node getChild(int index){return children.get(index);}
        void evaluate(){;}
    }
     Node root;
     Tree (Board rootBoard)
     {
        root = new Node(rootBoard.clone());
     }  
    void evaluateNextMove()
    {
         int optimal = miniMax(root, true, 0, (int) Double.NEGATIVE_INFINITY, (int)Double.POSITIVE_INFINITY);
         System.out.println("final gen size :" + root.getNumOfChildren() + "with oprtimal: " + optimal);
         for (int i=0; i < root.getNumOfChildren(); i++ ) {
            System.out.print(root.getChild(i).getValue() + ", ");  
         }
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
            for (int child=0; child < generation_limit;child++) 
                {
                    Node newChild = generateChild(root, '0');
                    root.addChild(newChild);
                    int value = miniMax(root.getChild(child), false, depth+1, alpha, beta);
                    bestValue = Math.max(bestValue, value);
                    alpha = Math.max(alpha, bestValue);
                    if (beta <= alpha) { root.removeChild(child); break;}
                
                }
                 root.getBoard().displayBoard();
            return bestValue;
        }
        else
        {
            bestValue = (int) Double.POSITIVE_INFINITY;
            for (int child=0; child< generation_limit ;child++) 
                {
                    Node newChild = generateChild(root, 'X');
                    root.addChild(newChild);
                    int value = miniMax(root.getChild(child), true, depth+1, alpha, beta);
                    bestValue = Math.min(bestValue, value);
                    beta = Math.min(beta, bestValue);
                    if (beta <= alpha) { root.removeChild(child); break;}
                }
                root.getBoard().displayBoard();
            return bestValue;
        }
    }
    Node generateChild(Node parent, char symbol)
    {
        int row, col;
        Board temp = new Board(parent.getBoard());
    
        do{
            row =rand.nextInt(8); col = rand.nextInt(8);
        }while(!isSquareEmpty(parent.getOpenTiles(),row,col)); // check if move is new

        temp.stamp(symbol, row, col); // add new move to board copy
        parent.getOpenTiles().stamp(symbol,row,col); // remove one empty tile from avail_tiles
        return new Node(temp);
    }
    private boolean isSquareEmpty(Board target, int r, int c){
        if (target.at(r,c) != '-')
            return false;
        return true;
    }

}