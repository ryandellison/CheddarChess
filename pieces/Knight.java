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
	public Moves move(Pair coord)
	{
		Moves moves = new Moves();
		int x = coord.getX();
		int y = coord.getY();
		moves.addMove(new Pair(x+2, y-1));
		moves.addMove(new Pair(x+2, y+1));
		moves.addMove(new Pair(x-2,y+1));
		moves.addMove(new Pair(x-2, y-1));
		return moves;
	}
}
