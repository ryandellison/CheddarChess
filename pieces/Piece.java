/*
 * Piece
 *
 * This abstract class represents what all Chess pieces have
 * in common and is extended by the other pieces.
 *
 * Methods:
 * 	boolean getColor()
 * 	getName()
 * 	move()
 *
 */

package pieces;


public abstract class Piece
{
	private String name;	// The "name" or type of the piece (Queen, Rook, Pawn, etc)
	private boolean color;	// The color of the piece, representing it's owner
  
	public Piece(boolean color, String name)
	{
		this.color = color;
		this.name = name;
	}

	/*
	 * getColor()
	 *
	 * The purpose of this method is to return the color
	 * of the piece.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	boolean color	// color of the piece
	 *
	 */
  
	public boolean getColor()
	{ 
		return color; 
	}

	/*
	 * getName()
	 *
	 * The purpose of this method is to return the name
	 * of the piece.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	String name	// name of the piece
	 *
	 */

	public String getName()
	{
		return name;
	}

	public abstract Moves move(Pair coord);
  
}

