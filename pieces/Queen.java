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
		int row = coord.getRow();
		int col = coord.getCol();
		for(int i = -7; i < 7; i++){//bishop moves
			moves.addMove(new Pair(row + i, col + i));
		}
		for(int i = -7; i < 7; i++){
			moves.addMove(new Pair(row - i, col + i));
		}
		//rook moves
		for(int i = -7; i < 7; i++){
			moves.addMove(new Pair(row + i, col));
	}
		for(int i = -7; i < 7; i++){
			moves.addMove(new Pair(row, col+i));
		}
		moves.checkBounds();
		return moves;

	}
}
