/*
 * Player
 *
 * The purpose of this class is to represent a player in the chess
 * game.
 *
 * Methods:
 * 	public int getNumPoints()
 *	public void addPoints(int numPoints)
 *	public void removePoints(int numPoints)
 *	public String getPlayerName()
 *	public Graveyard getGraveyard()
 */

package player;

import pieces.Piece;

import static constant.Colors.LIGHT;

public class Player 
{
	private String playerName;	// the name of the player, either "LIGHT" or "DARK"
	private int numPoints;		// number of points the player has
	private Graveyard graveyard;	// the players graveyard

	public Player(boolean color)
	{
		// determine the name based on the color of the player
		if(color == LIGHT)
			playerName = "LIGHT";
        	else
			playerName = "DARK";
	
		graveyard = new Graveyard();
		numPoints = 0;
	}

	/*
	 * getNumPoints()
	 *
	 * Returns the number of points the player has.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Return:
	 * 	int numPoints	// the number of points
	 */

	public int getNumPoints() 
	{
		return numPoints;
	}

	/*
	 * addPoints()
	 *
	 * Adds the given number of points to the current
	 * number of points the player has.
	 *
	 * Input:
	 * 	int numPoints	// number of points to add
	 *
	 * Output:
	 * 	N/A
	 */

	public void addPoints(int numPoints) 
	{
		this.numPoints += numPoints;
	}

	/*
	 * removePoints()
	 *
	 * Removes the given number of points from the
	 * current number of points the player has.
	 *
	 * Input:
	 * 	int numPoints	// number of points to remove
	 *
	 * Output:
	 * 	N/A
	 */

	public void removePoints(int numPoints)
	{
		// Remove the number of points if the player has at least that many, else print an error message
		if(numPoints <= this.numPoints)
			this.numPoints -= numPoints;
		else
			System.out.printf("ERROR in Player.removePoints(): %d is more points than the player currently has\n", numPoints);
	}

	/*
	 * getPlayerName()
	 *
	 * Returns the players name.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	String playerName	// name of the player
	 */	

	public String getPlayerName() 
	{
		return playerName;
	}

	/*
	 * getGraveyard()
	 *
	 * Returns the players graveyard.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	Graveyard graveyard	// the graveyard... duh
	 */

	public Graveyard getGraveyard() 
	{
		return graveyard;
	}

}

