package pieces;
import java.awt.Color;
import java.util.Set;

public abstract class Piece{
  private String name;
  private boolean color;
  
  public Piece(boolean c, String n){
    name = n;
    color = c;
  }
  public abstract Set<Square> move(Pair coord);
  
}
