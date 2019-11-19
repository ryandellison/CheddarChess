/*
 * Board
 *
 * The purpose of this class is to logically represent our chess board
 * using a 2-dimensional array of Square objects.
 *
 * Methods:
 * 	private void fillBoard()
 * 	public void enableSquares(Moves moves)
 * 	public void disableAllSquares()
 * 	public Pair findKing(boolean kingColor)
 * 	public void highlightSquares(Moves moves)
 * 	public void unhighlightAllSquares()
 * 	public void highlightEnableTopRows()
 * 	public void highlightEnableBottomRows()
 * 	public Square getSquare(Pair pair)
 * 	public Square getSquare(int row, int col)
 * 	public void enablePiecesByColor(boolean color)
 * 	public Moves getValidMoves(Pair location)
 */

package board;

import pieces.*;

import java.util.ArrayList;

import static constant.Colors.LIGHT;
import static constant.Colors.DARK;

public class Board
{
	private Square[][] board;	// our board, a 2-D array of Squares

	public Board()
	{
		board = new Square[8][8];

		fillBoard();
	}
	public Board(ArrayList<String[]> data)
	{
		board = new Square[8][8];
		fillSavedBoard(data);
	}

	private void fillSavedBoard(ArrayList<String[]> data)
	{
		String alias,pieceColor,tileColor,piece,enable;
		int index = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				alias = data.get(index)[0];
				pieceColor = data.get(index)[3];
				tileColor = data.get(index)[2];
				piece = data.get(index)[1];
				enable = data.get(index)[4];
				board[i][j] = new Square(alias, convertColor(tileColor),convertToPiece(piece,pieceColor));
				if(enable.equals("false"))
					board[i][j].disable();
				else
					board[i][j].enable();
				index++;
			}
		}
	}
	private boolean convertColor(String value)
	{
		return value.equals("true")  ? LIGHT : DARK;
	}

	private Piece convertToPiece(String name, String colorValue)
	{
		Piece piece;
		boolean color = convertColor(colorValue);
		switch (name){
			case "Pawn":
				piece = new Pawn(color,name);
				break;
			case "Rook":
				piece = new Rook(color,name);
				break;
			case "Knight":
				piece = new Knight(color,name);
				break;
			case "Bishop":
				piece = new Bishop(color,name);
				break;
			case "King":
				piece = new King(color,name);
				break;
			case "Queen":
				piece = new Queen(color,name);
				break;
			default:
				piece = null;
				break;
		}
		return piece;
	}

	/*
	 * fillBoard()
	 *
	 * The purpose of this method is to fill the board to it's default, starting
	 * state.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	N/A
	 */

	private void fillBoard()
	{
		int row;
		int col;
		boolean alternator;	// an alternator that keeps switching to help us fill in the Square colors

		alternator = LIGHT;	// top-left square of a chess board is LIGHT

		/*
		 * We fill each row by starting at the top-most row (board[0] in the array) and work our way down,
		 * adding to each row from the left-most column (board[row][0] in the array) to the right.
		 */

		// Fill the top-most row of the board
		board[0][0] = new Square("A8", LIGHT, new Rook(DARK, "Rook"));
		board[0][1] = new Square("B8", DARK, new Knight(DARK, "Knight"));
		board[0][2] = new Square("C8", LIGHT, new Bishop(DARK, "Bishop"));
		board[0][3] = new Square("D8", DARK, new Queen(DARK, "Queen"));
		board[0][4] = new Square("E8", LIGHT, new King(DARK, "King"));
		board[0][5] = new Square("F8", DARK, new Bishop(DARK, "Bishop"));
		board[0][6] = new Square("G8", LIGHT, new Knight(DARK, "Knight"));
		board[0][7] = new Square("H8", DARK, new Rook(DARK, "Rook"));

		// Fill the second top-most row of the board
		for(col = 0; col < 8; col++)
		{
			String letter = Character.toString((char)((int)('A')+col));

			if(col % 2 == 0)
				board[1][col] = new Square(letter + "7", DARK, new Pawn(DARK, "Pawn"));
			else
				board[1][col] = new Square(letter + "7", LIGHT, new Pawn(DARK, "Pawn"));
		}

		// Fill the middle rows
		for(row = 2; row < 6; row++)
		{

			for(col = 0; col < 8; col++)
			{
				String letter = Character.toString((char)((int)('A')+col));
				if(alternator)
				{
					board[row][col] = new Square(letter + Integer.toString(6 - (row - 2)), LIGHT, null);
					alternator = DARK;
				}
				else
				{
					board[row][col] = new Square(letter + Integer.toString(6 - (row - 2)), DARK, null);
					alternator = LIGHT;
				}
			}

			alternator = !alternator;
		}

		// Fill the second to bottom row of the board
		for(col = 0; col < 8; col++)
		{
			String letter = Character.toString((char)((int)('A')+col));

			if(col % 2 == 0)
				board[6][col] = new Square(letter + "2", LIGHT, new Pawn(LIGHT, "Pawn"));
			else
				board[6][col] = new Square(letter + "2", DARK, new Pawn(LIGHT, "Pawn"));
		}

		// Fill the bottom row of the board
		board[7][0] = new Square("A1", DARK, new Rook(LIGHT, "Rook"));
		board[7][1] = new Square("B1", LIGHT, new Knight(LIGHT, "Knight"));
		board[7][2] = new Square("C1", DARK, new Bishop(LIGHT, "Bishop"));
		board[7][3] = new Square("D1", LIGHT, new Queen(LIGHT, "Queen"));
		board[7][4] = new Square("E1", DARK, new King(LIGHT, "King"));
		board[7][5] = new Square("F1", LIGHT, new Bishop(LIGHT, "Bishop"));
		board[7][6] = new Square("G1", DARK, new Knight(LIGHT, "Knight"));
		board[7][7] = new Square("H1", LIGHT, new Rook(LIGHT, "Rook"));

	}

	/*
	 * enableSquares()
	 *
	 * The purpose of this method is to enable the squares
	 * on the board given the moves passed.
	 *
	 * Input:
	 * 	Moves moves	// moves to enable
	 *
	 * Output:
	 * 	N/A
	 */

	public void enableSquares(Moves moves)
	{
		Pair p;		// the current pair from moves
		int size;	// number of moves
		int i;

		// get the number of moves
		size = moves.getSize();

		// go through each pair in moves and enable it on the board
		for(i = 0; i < size; i++)
		{
			p = moves.getPair(i);

			board[p.getRow()][p.getCol()].enable();
		}
	}

	/*
	 * disableAllSquares()
	 *
	 * The purpose of this method is to disable all of the
	 * squares on the board.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	N/A
	 */

	public void disableAllSquares()
	{
		int i, j;

		// Go through all of the squares
		for(i = 0; i < 8; i++)
			for(j = 0; j < 8; j++)
				board[i][j].disable();
	}

	/*
	 * findKing()
	 *
	 * The purpose of this method is to find the location of
	 * the king on the board and return it as a Pair.
	 *
	 * Input:
	 * 	boolean kingColor	// the king color
	 *
	 * Output:
	 * 	Pair ans	// location of the king
	 */

	public Pair findKing(boolean kingColor)
	{
		Piece p;		// current piece
		Pair ans = null;	// location of the king
		
		// Go through all of the squares
		for (int row = 0; row < 8; row++)
		{
			for (int col = 0; col < 8; col++)
			{
				p = board[row][col].getPiece();

				if(p instanceof King && p.getColor() == kingColor)
				{
					// We found the king of the right color
					ans = new Pair(row, col);
				}
			}
		}

		return ans;
	}

	/*
	 * highlightSquares()
	 *
	 * The purpose of this method is to highlight the squares
	 * given the move locations.
	 *
	 * Input:
	 * 	Moves moves	// the move locations
	 *
	 * Output:
	 * 	N/A
	 */

	public void highlightSquares(Moves moves)
	{
		Pair p;		// current pair
		int size;	// number of moves
		int i;
		
		// Get the number of moves
		size = moves.getSize();

		// Go through each pair and highlight it
		for(i = 0; i < size; i++)
		{
			p = moves.getPair(i);

			board[p.getRow()][p.getCol()].setHighlighted(true);
		}

	}

	/*
	 * unhighlightAllSquares()
	 *
	 * The purpose of this method is to unhighlight all
	 * of the squares on the board.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	N/A
	 */

	public void unhighlightAllSquares()
	{
		int i, j;

		// Go through every square and unhighlight it
		for(i = 0; i < 8; i++)
			for(j = 0; j < 8; j++)
				board[i][j].setHighlighted(false);

	}

	/*
	 * highlightEnableBottomRows()
	 *
	 * The purpose of this method is to highlight and enable
	 * ONLY the squares from the bottom two rows that do not
	 * have any pieces on them.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	N/A
	 */

	public void highlightEnableBottomRows()
	{
		unhighlightAllSquares();
		disableAllSquares();

		// Go through those two rows
		for(int row = 6; row < 8; row++)
		{
			for(int col = 0; col < 8; col++)
			{
				// If there's not a piece there, hightlight and enable it
				if(board[row][col].getPiece() == null)
				{
					board[row][col].setHighlighted(true);
					board[row][col].enable();
				}
			}
		}
	}

	/*
	 * highlightEnableTopRows()
	 *
	 * The purpose of this method is to highlight and enable
	 * ONLY the square from the top two rows that do not
	 * have any pieces on them.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	N/A
	 */

	public void highlightEnableTopRows()
	{
		unhighlightAllSquares();
		disableAllSquares();

		// Go through those two rows
		for(int row = 0; row < 2; row++)
		{
			for(int col = 0; col < 8; col++)
			{
				// If there's not a piece there, highlight and enable it
				if(board[row][col].getPiece() == null)
				{
					board[row][col].setHighlighted(true);
					board[row][col].enable();
				}
			}
		}
	}

	/*
	 * getSquare()
	 *
	 * The purpose of this method is to get the square
	 * given at the location from the pair.
	 *
	 * Input:
	 * 	Pair pair	// location of the Square
	 *
	 * Output:
	 * 	Square square	// the square
	 */

	public Square getSquare(Pair pair)
	{
		int row;
		int col;

		row = pair.getRow();
		col = pair.getCol();

		// Make sure the row and col are in bounds
		if(((row >= 0) && (row < 8)) && ((col >= 0) && (col < 8)))
			return board[pair.getRow()][pair.getCol()];
		
		// Print error message
		System.out.printf("ERROR in Square.getSquare(): Cannot get square at (%d, %d)\n", row, col);
		return null;
		
	}

	/*
	 * getSquare()
	 *
	 * The purpose of this method is to get the square
	 * give at the given row and col.
	 *
	 * Input:
	 * 	int row	// the row
	 * 	int col // the col
	 *
	 * Output:
	 * 	Square square	// the square
	 */

	public Square getSquare(int row, int col)
	{
		// Make sure the row and col are in bounds
		if(((row >= 0) && (row < 8)) && ((col >= 0) && (col < 8)))
			return board[row][col];

		// Print error message
		System.out.printf("ERROR in Square.getSquare(): Cannot get square at (%d, %d)\n", row, col);
		return null;
	}

	/*
	 * enablePiecesByColor()
	 *
	 * The purpose of this method is to enable the pieces
	 * of a specified color.
	 *
	 * Input:
	 * 	boolean color	// the color
	 *
	 * Output:
	 * 	N/A
	 */

	public void enablePiecesByColor(boolean color)
	{
		Piece p; // the current piece
		int i, j;

		// Go through every square on the board, and enable all pieces of the right color
		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				// Get the piece
				p = board[i][j].getPiece();
				
				// If there is a piece, enable it if it's the right color
				if(p != null)
				{
					if(p.getColor() == color)
						board[i][j].enable();
					else
						board[i][j].disable();
				}
			}
		}
	}

    	/*
         * getValidMoves()
	 *
         * The purpose of this method is to validate the moves of the piece at a Pair location.
	 * Some method, huh?
	 *
	 * Input:
	 * 	Pair location	// location of the piece to get the valid moves for
	 *
	 * Output:
	 * 	Moves validMoves	// the valid moves
	 *
         */

	public Moves getValidMoves(Pair location)
	{
		Piece p;
		Piece potentialMove;
		Moves allMoves;		// the list of all moves, being thinned down to be the list of all valid moves
		Pair currentPair;	// the current pair being looked at
		Piece currentPiece;	// the current piece being looked
		int index;		// an index place-holder
		int c;			// the column of the main piece
		int r;			// the row of the main piece
		int curRow;		// the row of the currentPair
		int curCol;		// the col of the currentPair

		p = getSquare(location).getPiece();
		r = location.getRow();
		c = location.getCol();

		// No piece on this square, it shouldn't have been passed!
		if(p == null)
		{
			System.out.printf("ERROR in Board.getValidMoves(): No piece at (%d,%d)\n", r, c);
			return null;
		}

		// Get all the possible moves for our piece
		allMoves = p.move(location);

		// We traverse every possible move and determine whether or not the piece can move there
		for(int i = 0; i < allMoves.getSize(); i++)
		{
			currentPair = allMoves.getPair(i);

			currentPiece = getSquare(currentPair).getPiece();

			curRow = currentPair.getRow();
			curCol = currentPair.getCol();

			if(currentPiece != null)
			{

				/*
				 * The following removes unreachable moves from Rooks,
				 * Bishops, and Queens, which move like Rooks and Bishops.
				 */

				if(p instanceof Rook || p instanceof Queen) // The piece we're working with is a Rook or Queen, remove unreachable moves
				{
					if(r == curRow) // The Rook/Queen is on the same row as another piece
					{
						if(c < curCol) // The Rook/Queen is to the left of the other piece
						{
							// Remove all moves to the right of that piece, if they're in the possible list of moves
							for(int col = 1; (col + curCol) < 8; col++)
							{
								index = allMoves.findPair(curRow, col + curCol);
								
								if(index != -1)
									allMoves.removeMove(index);
							}
						}
						else // The Rook/Queen is to the right of the other piece
						{
							// Remove all moves to the left of that piece, if they're in the possible list of moves
							for(int col = 1; (curCol - col) >= 0; col++)
							{
								index = allMoves.findPair(curRow, curCol - col);

								if(index != -1)
									allMoves.removeMove(index);
							}

						}
						
						// Reset our indexing
						i = allMoves.findPair(curRow, curCol);
					}
					else if(c == curCol) // The Rook/Queen is on the same column as another piece
					{
						if(r < curRow) // The Rook/Queen is above the other piece
						{
							// Remove all moves below that piece, if they're in the possible list of moves
							for(int row = 1; (curRow + row) < 8; row++)
							{
								index = allMoves.findPair(curRow + row, curCol);

		
								if(index != -1)
									allMoves.removeMove(index);
							}
						}
						else // The Rook/Queen is below the other piece
						{
							// Remove all moves above that piece, if they're in the possible list of moves
							for(int row = 1; (curRow - row) >= 0; row++)
							{
								index = allMoves.findPair(curRow - row, curCol);

	
								if(index != -1)
									allMoves.removeMove(index);
							}
						}
						
						// Reset our indexing
						i = allMoves.findPair(curRow, curCol);
					}
				}

				if(p instanceof Bishop || p instanceof Queen) // The piece we're working with is a Bishop/Queen, remove unreachable moves
				{
					int tRow;
					int tCol;

					tRow = r - curRow;
					tCol = c - curCol;

					if(Math.abs(tRow) == Math.abs(tCol)) // The other piece is on a same diagonal as our main piece, find out which one
					{
					
						if((tRow > 0) && (tCol > 0)) // It's in the north-west diagonal
						{
							// Remove all the moves past the other piece on that diagonal in the same direction
							for(int t = 1; ((curRow - t) >= 0) && ((curCol - t) >= 0); t++)
							{
								index = allMoves.findPair((curRow - t), (curCol - t));

								if(index != -1)
									allMoves.removeMove(index);
							}
						}
						else if((tRow < 0) && (tCol > 0)) // It's in the south-west diagonal
						{
							// Remove all the moves past the other piece on that diagonal in the same direction
							for(int t = 1; ((curRow + t) < 8) && ((curCol - t) >= 0); t++)
							{
								index = allMoves.findPair((curRow + t), (curCol - t));

								if(index != -1)
									allMoves.removeMove(index);
							}
						}
						else if((tRow < 0) && (tCol < 0)) // It's in the south-east diagonal
						{
							// You get...
							for(int t = 1; ((curRow + t) < 8) && ((curCol + t) < 8); t++)
							{
								index = allMoves.findPair((curRow + t), (curCol + t));

								if(index != -1)
									allMoves.removeMove(index);
							}

						}
						else if((tRow > 0) && (tCol < 0)) // It's in the north-east diagonal
						{
							// the point...
							for(int t = 1; ((curRow - t) >= 0) && ((curCol + t) < 8); t++)
							{
								index = allMoves.findPair((curRow - t), (curCol + t));

								if(index != -1)
									allMoves.removeMove(index);
							}

						}
						
						// Reset our indexing
						i = allMoves.findPair(curRow, curCol);

					}

				}


				// If the our main piece is the same color as the other piece, we don't wanna capture it, so remove it as a move
				if(p.getColor() == currentPiece.getColor())
				{
					allMoves.removeMove(i);
					i--;

					// If our main piece was a Pawn...
					if(p instanceof Pawn)
					{
						if(p.getColor() == LIGHT)
						{
							index = allMoves.findPair(r - 2, c);
							if(index != -1)
								allMoves.removeMove(index);
						}
						else
						{
							index = allMoves.findPair(r + 2, c);
							if(index != -1)
								allMoves.removeMove(index);
						}
					}
				}
			}
		}

		// The following code is done only for Pawns
		if(p instanceof Pawn)
		{	
			/*
			 * This code determines if the Pawn can perform a diagonal-take in its
			 * direction and adds that move to the list of all moves.
			 */

			if(p.getColor() == LIGHT)
			{
				// Make sure the pawn's not at the end of the board
				if(r != 0)
				{
					// Check the first diagonal if possible
					if (c + 1 < 8)
					{
						currentPiece = getSquare(r - 1, c + 1).getPiece();
						if (currentPiece != null && currentPiece.getColor() != LIGHT) 
						{
							allMoves.addMove(new Pair(r - 1, c + 1));
						}
					}
					
					// Check the second diagonal if possible
					if (c - 1 >= 0)
					{
						currentPiece = getSquare(r - 1, c - 1).getPiece();
						if(currentPiece != null && currentPiece.getColor() != LIGHT) 
						{

							allMoves.addMove(new Pair(r - 1, c - 1));
						}
					}
				}

			}
			else
			{
				// Make sure the pawn's not at the end of the board
				if(r != 7)
				{
					// Check the first diagonal if possible
					if(c + 1 < 8)
					{
						currentPiece = this.getSquare(r + 1, c + 1).getPiece();
						if(currentPiece != null && currentPiece.getColor() != DARK)
						{

							allMoves.addMove(new Pair(r + 1, c + 1));
						}
					}

					// Check the second diagonal if possible
					if(c - 1 >= 0)
					{
						currentPiece = this.getSquare(r + 1, c - 1).getPiece();
						if(currentPiece != null && currentPiece.getColor() != DARK)
						{
							allMoves.addMove(new Pair(r + 1, c - 1));
						}
					}
				}
			}

			/*
			 * This code removes the Pawns ability to move two spaces ahead
			 * on its first move if there's a piece in front of it and remove
			 * the ability to move two spaces ahead if there's a piece on that
			 * second space.
			 */

			if(allMoves.getSize() > 0)
			{
				if(p.getColor() == LIGHT)
				{
					// Get the move right in front of the pawn
					index = allMoves.findPair(r - 1, c);

					// If it's still a possible move...
					if(index != -1)
					{
						currentPair = allMoves.getPair(index);
						currentPiece = getSquare(currentPair).getPiece();
						
						// Remove the move two spaces in front of the pawn if there's a piece right in front of the pawn
						if(currentPiece != null)
						{
							allMoves.removeMove(index);
						

							index = allMoves.findPair(r - 2, c);
							if(index != -1)
								allMoves.removeMove(index);
						}
						
						index = allMoves.findPair(r - 2, c);
						
						// Remove the move two spaces in front of the pawn if there's a piece there
						if(index != -1)
						{
							currentPair = allMoves.getPair(index);
							currentPiece = getSquare(currentPair).getPiece();

							if(currentPiece != null)
								allMoves.removeMove(index);
						}
					}

				}
				else
				{
					// Get the move right in front of the pawn
					index = allMoves.findPair(r + 1, c);

					// If it's still a possible move...
					if(index != -1)
					{
						currentPair = allMoves.getPair(index);
						currentPiece = getSquare(currentPair).getPiece();
						
						// Remove the move two spaces in front of the pawn if there's a piece right in front of the pawn
						if(currentPiece != null)
						{
							allMoves.removeMove(index);
						

							index = allMoves.findPair(r + 2, c);
							if(index != -1)
								allMoves.removeMove(index);
						}

						index = allMoves.findPair(r + 2, c);

						// Remove the move two spaces in front of the pawn if there's a piece there
						if(index != -1)
						{
							currentPair = allMoves.getPair(index);
							currentPiece = getSquare(currentPair).getPiece();

							if(currentPiece != null)
								allMoves.removeMove(index);
						}
					}

				}
				
				
			}

		}
		return allMoves;
	}

}

