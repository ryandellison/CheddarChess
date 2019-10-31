package pieces;
import java.awt.Color;

public abstract class Piece{
  private String name;
  private Color color;
  
  public Piece(Color c, String n){
    name = n;
    color = c;
  }
  public abstract Square move();
  
}
