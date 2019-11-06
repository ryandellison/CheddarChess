package pieces;
import java.util.HashSet;
import java.util.Set;


public class Bishop extends Piece{

    public Bishop(boolean color, String name){
        super(color, name);
    }

    public Set<Pair> move(Pair coord){
        Set<Pair> possibleSpots = new HashSet<>();
        int x = coord.getX();
        int y = coord.getY();
        for(int i = -7; i < 7; i++){
            possibleSpots.add(new Pair(x + i, y + i));
        }
        return possibleSpots;
    }










}