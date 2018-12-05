package fourinline2;
import java.util.ArrayList;
import java.util.Collections;   
import java.util.Random;
public class Tree {
    private static Random rand = new Random();
     Node root;
     Tree (Board rootBoard)
     {
        root = new Node(rootBoard.clone());
     }  
    Move evaluateNextMove()
    {
        int size = 10;
        ArrayList<Integer> values = new ArrayList<>();
        createFirstGen(root, size);
        for (int i=0; i < size; i++ )
            values.add(miniMax(root.getChild(i), false, 0, (int) Double.NEGATIVE_INFINITY, (int)Double.POSITIVE_INFINITY));
        int optimal = Collections.max(values), opt_index = values.indexOf(optimal);
        Node nextMove = root.getChild(opt_index);
        return nextMove.getNewMove();
    }
    private void createFirstGen(Node start, int gen_size)
    {
        for (int i=0; i < gen_size; i++ )
            start.addChild(generateChild(start, '0'));
    }
    int miniMax(Node root, boolean isMax, int depth, int alpha, int beta)
    {
        int bestValue, generation_limit = 10;
        if(depth == 4){
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
                    if (beta <= alpha) break;
                
                }
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
                    if (beta <= alpha)  break;
                }
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
        Node newChild = new Node(temp);
        newChild.addNewMove(row, col);
        return newChild;
    }
    private boolean isSquareEmpty(Board target, int r, int c){
        if (target.at(r,c) != '-')
            return false;
        return true;
    }

}