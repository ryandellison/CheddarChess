/*
 * Pair
 *
 * The purpose of this class is to represent a row and
 * column pair indicating a location on the chessboard
 * using two-dimensional array notation.
 *
 * Methods:
 * 	public int getRow()
 * 	public int getCol()
 */

package pieces;

public class Pair
{

	private int row;	// the row
	private int col;	// the column

	public Pair(int row, int col)
    	{        
		this.row = row;
        	this.col = col;
    	}

	/**
	 * getRow()
	 *
	 * The purpose of this method is to return the row
	 * of the pair.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	int row	// the row
	 */

    	public int getRow()
    	{
        	return row;
    	}

	/**
	 * getCol()
	 *
	 * The purpose of this method is to return the col
	 * of the pair.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	int col	// the column
	 */

    	public int getCol()
    	{
        	return col;
    	}  
    
}

