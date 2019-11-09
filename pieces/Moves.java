/*
 * Moves
 *
 * The purpose of this class is to represent a list of changing size of
 * possible moves (Pair's) for a piece.
 * The moves are only valid IFF isValid is true.
 *
 * Written by: Ryan Ellison
 *
 */

package pieces;

import java.util.*;

public class Moves
{
	private LinkedList<Pair> moves;	// the linkedlist of Pair moves
	private boolean isInBounds;	// indicates whether or not the moves are valid

	/*
	 * Moves()
	 *
	 * The purpose of this constructor is to create an empty list of moves
	 * with a default of them being invalid.
	 *
	 */

	public Moves()
	{
		moves = new LinkedList<Pair>();
		isInBounds = false;
	}

	/*
	 * addMove()
	 *
	 * The purpose of this method is to add a move (Pair) to the list of moves. This appends
	 * the move to the end of the list.
	 *
	 * Input:
	 * 	Pair p	// the move to insert
	 *
	 * Output:
	 * 	N/A
	 *
	 */

	public void addMove(Pair p)
	{
		moves.add(p);
		isInBounds = false;
	}

	/*
	 * getMoves()
	 *
	 * The purpose of this method is to return the list of moves.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	LinkedList<Pair> moves
	 *
	 */

	public LinkedList<Pair> getMoves()
	{
		return moves;
	}


	/*
	 * getSize()
	 *
	 * The purpose of this method is to return the size of the list of moves.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	int moves.size()
	 *
	 */

	public int getSize()
	{
		return moves.size();
	}

    /*
     * getPair()
     *
     * The purpose of this method is to return the Pair at a given index of the list of moves.
     *
     * Input:
     * 	int index
     *
     * Output:
     * 	Pair
     *
     */

    public Pair getPair(int index)
    {
        return this.moves.get(index);
    }

	/*
	 * getInBounds()
	 *
	 * The purpose of this method is to return the validity of
	 * the moves.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	boolean getInBounds	// indicates whether or not the moves are valid
	 *
	 */
	
	public boolean getInBounds()
	{
		return isInBounds;
	}

	/*
	 * checkBounds()
	 *
	 * The purpose of this method is to make sure the current moves are in bounds.
	 * This method checks if each pair is in range of our chess board,
	 * meaning the x and y coordinates must both be between 0 and 7.
	 * The move is removed if it is not in range.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	N/A
	 *
	 */

	public void checkBounds()
	{
		int i;
		Pair currentPair;

		currentPair = null;
		
		// traverse every move in the list
		for(i = 0; i < moves.size();)
		{	
			// get the current move we're looking at
			currentPair = moves.get(i);
			
			// validate the move and remove it if necessary
			if(currentPair.getX() < 0 || currentPair.getX() > 7)
				moves.remove(i);
			else if(currentPair.getY() < 0 || currentPair.getY() > 7)
				moves.remove(i);
			else
				i++;
		}
		isInBounds = true;
	}

	/*
	 * findPair()
	 *
	 * The purpose of this method is to find a Pair with the given
	 * coordinates and return its index. Returns -1 if not in moves.
	 *
	 * Input:
	 * 	int x	// x coordinate
	 * 	int y	// y coordinate
	 *
	 * Output:
	 * 	int index	// location
	 *
	 */

	public int findPair(int x, int y)
	{
		Pair currentPair;
		int index;
		int i;

		index = -1;

		for(i = 0; i < moves.size(); i++)
		{
			currentPair = moves.get(i);

			if((currentPair.getX() == x) && (currentPair.getY() == y))
			{
				index = i;
				i = moves.size();
			}
		}

		return index;

	}
}
