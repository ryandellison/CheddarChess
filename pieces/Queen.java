package pieces;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece
{

	public Queen(boolean color, String name)
	{
		super(color,name);
	}
	@Override
	public Moves move(Pair coord)
	{
		Moves moves = new Moves();
		int x = coord.getX();
		int y = coord.getY();
		for(int i = -7; i < 7; i++){//bishop moves
			moves.addMove(new Pair(x + i, y + i));
		}
		//rook moves
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
