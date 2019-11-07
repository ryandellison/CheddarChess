package pieces;
import java.util.Set;

public abstract class Piece{
	private String name;
	private boolean owner;
  
	public Piece(boolean o, String n){
		owner = o;
		name = n;
	}
  
	public boolean getOwner(){
		return owner;
	}

	public String getName(){
		return name;
	}

	public abstract Moves move(Pair coord);
  
}
