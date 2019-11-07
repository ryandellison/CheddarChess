package pieces;
import java.util.HashSet;
import java.util.Set;


public class Bishop extends Piece{

    public Bishop(boolean color, String name){
        super(color, name);
    }

    public Moves move(Pair coord){
        Moves moves = new Moves();
        int x = coord.getX();
        int y = coord.getY();
        for(int i = -7; i < 7; i++){
            moves.addMove(new Pair(x + i, y + i));
        }
        moves.checkBounds();
        return moves;
    }










}
