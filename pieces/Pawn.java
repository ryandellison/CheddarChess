package pieces;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece
{

	public Pawn(boolean color, String name)
	{
		super(color,name);
	}
	@Override
	public HashSet<Square> move(Pair coord)
	{
		HashSet<Square> possibleSpots = new HashSet<>();




		return possibleSpots;
	}

}