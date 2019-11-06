package pieces;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece
{
	private boolean firstMove;
	private boolean color;
	private String name;
	public Pawn(boolean color, String name)
	{//light color is true, dark color is false
		super(color,name);
		firstMove = true;
	}
	@Override
	public HashSet<Pair> move(Pair coord)
	{
		HashSet<Pair> possibleSpots = new HashSet<>();
		int x = coord.getX();
		int y = coord.getY();
		if(color){
			if(firstMove){
				possibleSpots.add(new Pair(x+2, y));
				firstMove = false;
			}
			possibleSpots.add(new Pair(x+1, y));
		}
		else{
			if(firstMove){
				possibleSpots.add(new Pair(x-2, y));
				firstMove = false;
			}
			possibleSpots.add(new Pair(x-1, y));
		}
		return possibleSpots;
	}

}