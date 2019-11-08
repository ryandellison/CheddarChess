package board;

import pieces.*;
import constant.Colors;

public class Square
{
	private String alias;
	private boolean color;
	private Piece piece;
	public Square(String alias, boolean color)
	{
		this.alias = alias;
		this.color = color;
		piece = null;
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
  
  
}
