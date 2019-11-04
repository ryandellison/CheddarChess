package pieces;

import java.util.Set;

public class Pawn extends Piece
{

	public Pawn(boolean color, String name)
	{
		super(color,name);
	}
	@Override
	public Set<Square> move(Pair coord)
	{
		return null;
	}
}