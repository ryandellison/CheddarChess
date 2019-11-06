package pieces;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece
{
	public Knight(boolean color, String name)
	{
		super(color,name);
	}

	@Override
	public Set<Pair> move(Pair coord)
	{
		Set<Pair>possibleSpots = new HashSet<>();
		int x = coord.getX();
		int y = coord.getY();
		possibleSpots.add(new Pair(x+2, y-1));
		possibleSpots.add(new Pair(x+2, y+1));
		possibleSpots.add(new Pair(x-2,y+1));
		possibleSpots.add(new Pair(x-2, y-1));
		return possibleSpots;
	}
}