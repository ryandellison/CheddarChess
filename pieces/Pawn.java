/*
 * Pawn
 *
 * The purpose of this class is to represent a Pawn chess piece
 * by extending a generic Piece.
 *
 * Methods:
 * 	move()
 *
 */

package pieces;

import static constant.Colors.LIGHT;
import static constant.Colors.DARK;

public class Pawn extends Piece
{
	private boolean firstMove;	// indicates whether or not it is the Pawns first move

	public Pawn(boolean color, String name)
	{
		super(color,name);
		firstMove = true;
	}

	/*
	 * move()
	 *
	 * The purpose of this method is to override the Piece's
	 * move method and, given a Pair representing the current
	 * location of the piece, will return a list of Pairs where
	 * the Pawn can move in-bounds. DOES NOT TAKE INTO ACCOUNT
	 * THE REST OF THE PIECES ON THE BOARD.
	 *
	 * Input:
	 * 	Pair current	// the current location of the Pawn
	 *
	 * Output:
	 * 	Moves moves	// the list of Pairs where the Pawn can move in-bounds
	 *
	 */

	@Override
	public Moves move(Pair current)
	{
		Moves moves = new Moves();
		int row = current.getRow();
		int col = current.getCol();
		
		if(getColor() == LIGHT) // Light colored pawn
		{
			// If it's the pawn first move, it can move 2 spaces forward
			if(firstMove)
				moves.addMove(new Pair(row - 2, col));

			moves.addMove(new Pair(row-1, col));
		}
		else // Dark colored pawn
		{
			// If it's the pawns first move, it can move 2 spaces forward
			if(firstMove)
				moves.addMove(new Pair(row+2, col));
			
			moves.addMove(new Pair(row+1, col));
		}

		// Ensure all the moves are in-bounds
		moves.checkBounds();
		
		return moves;
	}

	/*
	 * setFirstMoveToFalse()
	 *
	 * The purpose of this method is to set the pawns
	 * firstMove value to false.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	N/A
	 */

	public void setFirstMoveToFalse()
	{ 
		firstMove = false; 
	}

	/*
	 * setFirstMoveToTrue()
	 *
	 * The purpose of this method is to set the pawns
	 * firstMove value to true.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	N/A
	 */

	public void setFirstMoveToTrue()
	{
		firstMove = true;
	}

	/*
	 * getFirstMove()
	 *
	 * The purpose of this method is getting the value of
	 * firstMove.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	boolean firstMove	// the value of firstMove
	 */

	public boolean getFirstMove()
	{ 
		return firstMove; 
	}

}

