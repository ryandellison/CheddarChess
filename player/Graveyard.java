/*
 * Graveyard
 *
 * The purpose of this class is to represent a graveyard
 * where the dead/captured pieces of the player will go
 * and can be brought back from.
 * 
 */

package player;

import board.Square;
import pieces.*;

import java.util.ArrayList;

import static constant.Points.PAWN_VALUE;
import static constant.Points.ROOK_COST;
import static constant.Points.KNIGHT_COST;
import static constant.Points.BISHOP_COST;
import static constant.Points.QUEEN_COST;

public class Graveyard
{

	private ArrayList<Piece> graveyard;	// the graveyard as an ArrayList of Pieces

	public Graveyard()
	{
		graveyard = new ArrayList<Piece>();
	}

	/**
	 * addToGraveyard()
	 *
	 * The purpose of this method is to add a Piece
	 * to the graveyard.
	 *
	 * Input:
	 * 	Piece p	// the piece to add
	 *
	 * Output:
	 * 	N/A
	 */

	public void addToGraveyard(Piece p)
	{
		graveyard.add(p);
	}

	/**
	 * removeFromGraveyard()
	 *
	 * The purpose of this method is to remove a Piece
	 * from the graveyard.
	 *
	 * Input:
	 * 	Piece p	// the piece to remove
	 *
	 * Output:
	 * 	N/A
	 */

	public void removeFromGraveyard(Piece p)
	{
		graveyard.remove(p);
	}

	/**
	 * getNumPieces()
	 *
	 * The purpose of this method is to get the number of pieces
	 * in the graveyard.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	int numPieces	// number of pieces in graveyard
	 */

	public int getNumPieces()
	{
		return graveyard.size();
	}

	/**
	 * getPiece()
	 *
	 * The purpose of this method is to return a piece at
	 * the given index (which is first validated) in the
	 * graveyard.
	 *
	 * Input:
	 * 	int index	// location of piece
	 *
	 * Output:
	 * 	Piece p		// piece at that location
	 */

	public Piece getPiece(int index)
	{
		// Return the piece if the index is valid
		if((index >= 0) && (index <= graveyard.size()))
			return graveyard.get(index);
		
		// If not, print error message and return null
		System.out.printf("ERROR in Graveyard.getPiece(): Index out of bounds of %d\n", index);
		return null;
	}

	/**
	 * removePiece()
	 *
	 * The purpose of this method is to remove a piece at
	 * the given index (which is first validated) in the
	 * graveyard.
	 *
	 * Input:
	 * 	int index	// location of piece
	 *
	 * Output:
	 * 	N/A
	 */

	public void removePiece(int index)
	{
		// Remove the piece if the index is valid
		if((index >= 0) && (index <= graveyard.size()))
			graveyard.remove(index);

		// Print error message if not
		System.out.printf("ERROR in Graveyard.removePiece(): Index out of bounds of %d\n", index);
	}

	/**
	 * getCost()
	 *
	 * Gets the cost of a piece given the piece passed based
	 * on the imported constants for piece values.
	 *
	 * Input:
	 * 	Piece piece	// piece to get cost of
	 *
	 * Output:
	 * 	int cost	// cost of the piece
	 */

	public static int getCost(Piece piece)
	{
		if(piece instanceof Rook)
			return ROOK_COST;
		else if(piece instanceof Knight)
			return KNIGHT_COST;
		else if(piece instanceof Bishop)
			return BISHOP_COST;
		else if(piece instanceof Queen)
			return QUEEN_COST;

		return 0;
	}

}

