package pieces;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece
{

	public Rook(boolean color, String name)
	{
		super(color,name);
	}
	@Override
	public Set<Pair> move(Pair coord)
	{
		Set<Pair>possibleSpots = new HashSet<>();
		int x = coord.getX();
		int y = coord.getY();
		for(int i = -7; i < 7; i++){
			possibleSpots.add(new Pair(x + i, y));
		}
		for(int i = -7; i < 7; i++){
			possibleSpots.add(new Pair(x, y+i));
		}
		return possibleSpots;
	}
}