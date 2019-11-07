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
	public Moves move(Pair coord)
	{
		Moves moves = new Moves();
		int x = coord.getX();
		int y = coord.getY();
		for(int i = -7; i < 7; i++){
			moves.addMove(new Pair(x + i, y));
		}
		for(int i = -7; i < 7; i++){
			moves.addMove(new Pair(x, y+i));
		}
		moves.checkBounds();
		return moves;
	}
}
