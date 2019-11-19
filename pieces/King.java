/*
 * King
 *
 * The purpose of this class is to represent a King chess
 * piece by extending the Piece class.
 */

package pieces;

public class King extends Piece
{

	public King(boolean color, String name)
	{
		super(color,name);
	}

	/**
	 * move()
	 *
	 * The purpose of this method is to return a list of
	 * all possible moves the king can make. THIS DOES
	 * NOT TAKE INTO ACCOUNT THE REST OF THE BOARD.
	 *
	 * Input:
	 * 	Pair coord	// current location of the king
	 *
	 * Output:
	 * 	Moves move	// list of moves the king can make
	 */

	@Override
	public Moves move(Pair coord)
	{
		Moves moves = new Moves();
		int row = coord.getRow();
		int col = coord.getCol();
		
		// The king can one space in any direction
		moves.addMove(new Pair(row+1, col));
		moves.addMove(new Pair(row-1, col));
		moves.addMove(new Pair(row, col+1));
		moves.addMove(new Pair(row, col-1));
		moves.addMove(new Pair(row-1, col-1));
		moves.addMove(new Pair(row+1, col+1));
		moves.addMove(new Pair(row+1, col-1));
		moves.addMove(new Pair(row-1, col+1));

		// Checks the bounds of the moves
		moves.checkBounds();
		
		return moves;
	}

}

