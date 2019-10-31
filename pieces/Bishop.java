package pieces;
import java.util.HashSet;


public class Bishop extends Piece{

    public Bishop(boolean color, String name){
        super(color, name);
    }

    public HashSet<Square> move(Pair coord){
        Set<Square>ans = new HashSet<Square>();
        for(i = coord.getX(); i >= 0; i--){
            for(j = coord.getY(); j >= 0; j--){

            }
        }
        for(i = coord.getX(); i >= 0; i--){
            for(j = coord.getY(); j < 8; j++){

            }
        }
        for(i = coord.getX(); i < 8; i++){
            for(j = coord.getY(); j >= 0; j--){

            }
        }
        for(i = coord.getX(); i < 8; ){
            for(j = coord.getY(); j < 8; j++){

            }
        }
    }










}