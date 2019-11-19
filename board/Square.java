package board;

import pieces.*;
import constant.Colors;

public class Square
{
	private String alias; // A1...A8 etc
	private boolean color; // Color of square
	private Piece piece; // Piece in the square
	private boolean enabled; // clickable
	private boolean highlighted; // potential move

	/**
	 * Creates a square object based on the alias and color of the square
	 * @param alias
	 * @param color
	 */
	public Square(String alias, boolean color)
	{
		this.alias = alias;
		this.color = color;
		piece = null;

		enabled = false;
		highlighted = false;
	}

	/**
	 * Initializes the piece as well
	 * @param alias A1, A2... etc
	 * @param color Color of the square
	 * @param piece Piece
	 */
	public Square(String alias, boolean color, Piece piece)
	{
		this.alias = alias;
		this.color = color;
		this.piece = piece;
	}

	/**
	 * Returns the Alias of the square
	 * @return String
	 */
	public String getAlias()
	{
		return alias;
	}

	/**
	 * Returns the color of the square
	 * @return true is light
	 */
	public boolean getColor()
	{
		return color;
	}

	/**
	 * Returns the piece that is in the square, if any
	 * @return Piece
	 */
	public Piece getPiece()
	{
		return piece;
	}

	/**
	 * Removes the piece from the square
	 * @return
	 */
	public Piece popPiece()
	{
		Piece p = this.piece;
		this.piece = null;
		return p;
	}

	/**
	 * Sets the square with a piece
	 * @param p Piece
	 */
	public void setPiece(Piece p)
	{
		piece = p;
	}

	/**
	 * Enables the square
	 */
	public void enable()
	{
		enabled = true;
	}

	/**
	 * Disables the square
	 */
	public void disable()
	{
		enabled = false;
	}

	/**
	 * Returns if sqaure is enables
	 * @return True if enabled
	 */
	public boolean getEnabled()
	{
		return enabled;
	}

	/**
	 * Highlights the square
	 * @param highlighted boolean
	 */
	public void setHighlighted(boolean highlighted)
	{
		this.highlighted = highlighted;
	}

	/**
	 * Returns true if square is highlighted
	 * @return boolean
	 */
	public boolean getHighlighted()
	{
		return highlighted;
	}

	/**
	 * Used for saved the data to a file, gets all pertinent info
	 * @return String representation of the square
	 */
	@Override
	public String toString() {
		if(getPiece() != null) {
			return getAlias() + "," + getPiece().getName() + ","
					+ getColor() +"," +getPiece().getColor()+ ","
					+ getEnabled();
		}
		return getAlias()+","+"Empty,"+getColor()+","
				+"false"+","+getEnabled();
	}
}
