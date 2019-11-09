package board;

import pieces.*;
import constant.Colors;

public class Square
{
	private String alias;
	private boolean color;
	private Piece piece;
	private boolean enabled;
	private boolean highlighted;

	public Square(String alias, boolean color)
	{
		this.alias = alias;
		this.color = color;
		piece = null;

		enabled = false;
		highlighted = false;
  	}
  	
	public Square(String alias, boolean color, Piece piece)
	{
		this.alias = alias;
		this.color = color;
		this.piece = piece;
  	}

	public String getAlias()
	{
		return alias;
	}

	public boolean getColor()
	{
		return color;
	}

	public Piece getPiece()
	{
		return piece;
	}

	public void enable()
	{
		enabled = true;
	}

	public void disable()
	{
		enabled = false;
	}

	public boolean getEnabled()
	{
		return enabled;
	}

	public void setHighlighted(boolean highlighted)
	{
		this.highlighted = highlighted;
	}

	public boolean getHighlighted()
	{
		return highlighted;
	}
  
  
}
