package pieces;
import java.util.Set;

public abstract class Piece{
	private String name;
	private boolean color;
  
	public Piece(boolean c, String n){
		this.color = c;
		this.name = n;
	}
  
	public boolean getColor(){ return color; }

	public String getName(){
		return name;
	}

	public abstract Moves move(Pair coord);
  
}
