/*
 * Queen
 *
 * The purpose of this class is to represent a Queen chess
 * piece by extending the Piece class.
 */

package pieces;

public class Queen extends Piece
{

	public Queen(boolean color, String name)
	{
		super(color,name);
	}

	/*
	 * move()
	 *
	 * The purpose of this method is to return a list of all
	 * the possible moves the queen can make. DOES NOT TAKE INTO
	 * ACCOUNT THE REST OF THE BOARD.
	 *
	 * Input:
	 * 	Pair coord	// current location of the queen
	 *
	 * Output:
	 * 	Moves moves	// list of moves the queen can make
	 */

	@Override
	public Moves move(Pair coord)
	{
		Moves moves = new Moves();
		int row = coord.getRow();
		int col = coord.getCol();
		
		// The queen can make all the same moves a bishop can
		for(int i = -7; i < 8; i++)
			if(i != 0)
				moves.addMove(new Pair(row + i, col + i));
		for(int i = -7; i < 8; i++)
			if(i != 0)
				moves.addMove(new Pair(row - i, col + i));
		
		
		// The queen can make all the same moves a rook can
		for(int i = -7; i < 8; i++)
			if(i != 0)
				moves.addMove(new Pair(row + i, col));
		
		for(int i = -7; i < 8; i++)
			if(i != 0)
				moves.addMove(new Pair(row, col+i));
		
		// Ensure all the moves are in-bounds
		moves.checkBounds();

		return moves;
	}

}

