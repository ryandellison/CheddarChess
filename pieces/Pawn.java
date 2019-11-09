package pieces;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece
{
	private boolean firstMove;
	private boolean color;
	private String name;

	public Pawn(boolean color, String name)
	{//light color is true, dark color is false
		super(color,name);
		firstMove = true;
	}

	@Override
	public Moves move(Pair current)
	{
		Moves moves = new Moves();
		int row = current.getRow();
		int col = current.getCol();
		
		if(color){
			if(firstMove){
				moves.addMove(new Pair(row+2, col));
				firstMove = false;
			}
			moves.addMove(new Pair(row+1, col));
		}
		else{
			if(firstMove){
				moves.addMove(new Pair(row-2, col));
				firstMove = false;
			}
			moves.addMove(new Pair(row-1, col));
		}
		moves.checkBounds();
		return moves;
	}

}
