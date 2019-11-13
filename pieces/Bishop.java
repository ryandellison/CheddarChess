package pieces;
import java.util.HashSet;
import java.util.Set;


public class Bishop extends Piece{

    public Bishop(boolean color, String name){
        super(color, name);
    }

    public Moves move(Pair coord){
        Moves moves = new Moves();
        int row = coord.getRow();
        int col = coord.getCol();

        for(int i = -7; i < 7; i++){
            moves.addMove(new Pair(row + i, col + i));
        }
        for(int i = -7; i < 7; i++){
            moves.addMove(new Pair(row - i, col + i));
        }

        moves.checkBounds();
        return moves;
    }

    public String getUnicode(){
        if(this.getColor() == true)
        {
            return "\u2657";
        }
        else
        {
            return "\u265D";
        }
    }








}
