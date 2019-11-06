package pieces;
import java.util.Set;

public abstract class Piece{
  private String name;
  private boolean color;
  
  public Piece(boolean c, String n){
    name = n;
    color = c;
  }
  public boolean getColor(){
    return color;
  }

  public String getName(){
    return name;
  }

  public abstract Set<Pair> move(Pair coord);
  
}
