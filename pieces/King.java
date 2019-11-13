package pieces;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece
{

	public King(boolean color, String name)
	{
		super(color,name);
	}
	@Override
	public Moves move(Pair coord)
	{
		Moves moves = new Moves();
		int row = coord.getRow();
		int col = coord.getCol();
		moves.addMove(new Pair(row+1, col));
		moves.addMove(new Pair(row-1, col));
		moves.addMove(new Pair(row, col+1));
		moves.addMove(new Pair(row, col-1));
		moves.addMove(new Pair(row-1, col-1));
		moves.addMove(new Pair(row+1, col+1));
		moves.addMove(new Pair(row+1, col-1));
		moves.addMove(new Pair(row-1, col+1));
		moves.checkBounds();
	return moves;
	}

	public String getUnicode(){
		if(this.getColor() == true)
		{
			return "\u2655";
		}
		else
		{
			return "\u265B";
		}
	}
}
