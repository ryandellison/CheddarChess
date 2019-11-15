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
		int row = coord.getRow();
		int col = coord.getCol();
		for(int i = -7; i < 8; i++){
			if(i != 0)
				moves.addMove(new Pair(row + i, col));
		}
		for(int i = -7; i < 8; i++){
			if(i != 0)
				moves.addMove(new Pair(row, col+i));
		}
		moves.checkBounds();
		return moves;
	}

	public String getUnicode(){
		if(this.getColor() == true)
		{
			return "\u2656";
		}
		else
		{
			return "\u265C";
		}
	}
}
