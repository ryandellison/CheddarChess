package pieces;

import java.util.Set;

public class Queen extends Piece
{

	public Queen(boolean color, String name)
	{
		super(color,name);
	}
	@Override
	public Set<Square> move(Pair coord)
	{
		return null;
	}
}