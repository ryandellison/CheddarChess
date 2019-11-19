/*
 * Bishop
 *
 * The purpose of this class is to represent a Bishop chess
 * piece by extending the Piece class.
 */

package pieces;

public class Bishop extends Piece
{

    	public Bishop(boolean color, String name)
    	{
		super(color, name);
    	}


	/**
	 * move()
	 *
	 * The purpose of this method is to get a list of Pair
	 * moves that the Bishop can possibly make. THESE MOVES
	 * DO NOT TAKE INTO ACCOUNT THE REST OF THE BOARD.
	 *
	 * Input:
	 * 	Pair coord	// current location of the bishop
	 *
	 * Output:
	 * 	Moves moves	// the list of moves
	 */
	
	@Override
	public Moves move(Pair coord)
    	{
        	Moves moves = new Moves();
        	int row = coord.getRow();
        	int col = coord.getCol();

        	for(int i = -7; i < 8; i++)
			if(i != 0)
            			moves.addMove(new Pair(row + i, col + i));
        	
        	for(int i = -7; i < 8; i++)
			if(i != 0)
            			moves.addMove(new Pair(row - i, col + i));
		
		// Ensrue all the moves are in-bounds
        	moves.checkBounds();
        	return moves;
    	}

}

