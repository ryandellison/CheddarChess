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

	public void addToGraveyard(Piece p)
	{
		graveyard.add(p);
	}

	public int getNumPieces()
	{
		return graveyard.size();
	}

	public Piece getPiece(int index)
	{
		if((index >= 0) && (index <= graveyard.size()))
			return graveyard.get(index);
		
		System.out.printf("ERROR in Graveyard.getPiece(): Index out of bounds of %d\n", index);
		return null;
	}

	public void removePiece(int index)
	{
		if((index >= 0) && (index <= graveyard.size()))
			graveyard.remove(index);

		System.out.printf("ERROR in Graveyard.removePiece(): Index out of bounds of %d\n", index);
	}

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

