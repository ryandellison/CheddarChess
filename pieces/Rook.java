/*
 * Rook
 *
 * The purpose of this class is to represent a Rook
 * chess piece by extending the Piece class.
 */

package pieces;

public class Rook extends Piece
{

	public Rook(boolean color, String name)
	{
		super(color,name);
	}
	
	/**
	 * move()
	 *
	 * The purpose of this method is to return a list
	 * of all the possible moves a rook can make.
	 *
	 * Input:
	 * 	Pair coord	// current location of the rook
	 *
	 * Output:
	 * 	Moves move	// list of all moves
	 */

	@Override
	public Moves move(Pair coord)
	{
		Moves moves = new Moves();
		int row = coord.getRow();
		int col = coord.getCol();
		
		// Rooks can move anywhere in their column
		for(int i = -7; i < 8; i++)
			if(i != 0)
				moves.addMove(new Pair(row + i, col));
		
		// Rooks can move anywhere in their row
		for(int i = -7; i < 8; i++)
			if(i != 0)
				moves.addMove(new Pair(row, col+i));

		// Ensure the moves are in-bounds
		moves.checkBounds();

		return moves;
	}

}

