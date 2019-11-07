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
		int x = current.getX();
		int y = current.getY();
		
		if(color){
			if(firstMove){
				moves.addMove(new Pair(x+2, y));
				firstMove = false;
			}
			moves.addMove(new Pair(x+1, y));
		}
		else{
			if(firstMove){
				moves.addMove(new Pair(x-2, y));
				firstMove = false;
			}
			moves.addMove(new Pair(x-1, y));
		}

		moves.validateMoves();

		return moves;
	}

}
