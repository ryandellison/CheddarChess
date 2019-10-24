package pieces;
import java.awt.Color;

public abstract class Piece{
  private String name;
  private Spot location;
  private Color color;
  
  public Piece(Spot s, Color c, String n){
    name = n;
    color = c;
    location = s;
  }
  public abstract Spot move();
  
}
