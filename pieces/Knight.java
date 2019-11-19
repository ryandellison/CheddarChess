/*
 * Knight
 *
 * The purpose of this class is to represent a Knight chess
 * piece by extending the Piece class.
 */

package pieces;

public class Knight extends Piece
{
	public Knight(boolean color, String name)
	{
		super(color,name);
	}

	/*
	 * move()
	 *
	 * The purpose of this method is to return a list of all
	 * possible moves the knight can make. DOES NOT TAKE INTO
	 * ACCOUNT THE REST OF THE BOARD.
	 *
	 * Input:
	 *  	Pair coord	// the current location of the knight
	 *
	 * Output:
	 * 	Moves moves	// list of all possible moves
	 */

	@Override
	public Moves move(Pair coord)
	{
		Moves moves = new Moves();
		int row = coord.getRow();
		int col = coord.getCol();

		// The knight can move in any "L" shaped pattern
		moves.addMove(new Pair(row+2, col-1));
		moves.addMove(new Pair(row+2, col+1));
		moves.addMove(new Pair(row-2,col+1));
		moves.addMove(new Pair(row-2, col-1));
		moves.addMove(new Pair(row+1, col+2));
		moves.addMove(new Pair(row+1, col-2));
		moves.addMove(new Pair(row-1, col+2));
		moves.addMove(new Pair(row-1, col-2));

		// Ensure the moves are in-bounds
		moves.checkBounds();

		return moves;
	}

}

