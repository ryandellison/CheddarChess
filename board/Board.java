/*
 * Board
 *
 * The purpose of this class is to logically represent our chess board
 * using a 2-dimensional array of Square objects.
 *
 * Methods:
 * 	enableSquares()
 * 	disableAllSquares()
 * 	findKing()
 * 	highlightSquares()
 * 	unhighlightAllSquares()
 * 	getSquare()
 * 	enablePiecesByColor()
 * 	printBoard()
 * 	getValidMoves()
 */

package board;

import pieces.*;
import static constant.Colors.LIGHT;
import static constant.Colors.DARK;

public class Board
{
	private Square[][] board;

	public Board()
	{
		board = new Square[8][8];

		fillBoard();
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
	 *
	 */

	private void fillBoard()
	{
		int row;
		int col;
		boolean alternator;

		alternator = LIGHT;

		board[0][0] = new Square("A8", LIGHT, new Rook(DARK, "Rook"));
		board[0][1] = new Square("B8", DARK, new Knight(DARK, "Knight"));
		board[0][2] = new Square("C8", LIGHT, new Bishop(DARK, "Bishop"));
		board[0][3] = new Square("D8", DARK, new King(DARK, "King"));
		board[0][4] = new Square("E8", LIGHT, new Queen(DARK, "Queen"));
		board[0][5] = new Square("F8", DARK, new Bishop(DARK, "Bishop"));
		board[0][6] = new Square("G8", LIGHT, new Knight(DARK, "Knight"));
		board[0][7] = new Square("H8", DARK, new Rook(DARK, "Rook"));


		for(col = 0; col < 8; col++)
		{
			String letter = Character.toString((char)((int)('A')+col));

			if(col % 2 == 0)
				board[1][col] = new Square(letter + "7", DARK, new Pawn(DARK, "Pawn"));
			else
				board[1][col] = new Square(letter + "7", LIGHT, new Pawn(DARK, "Pawn"));
		}

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

		for(col = 0; col < 8; col++)
		{
			String letter = Character.toString((char)((int)('A')+col));

			if(col % 2 == 0)
				board[6][col] = new Square(letter + "2", LIGHT, new Pawn(LIGHT, "Pawn"));
			else
				board[6][col] = new Square(letter + "2", DARK, new Pawn(LIGHT, "Pawn"));
		}

		board[7][0] = new Square("A1", DARK, new Rook(LIGHT, "Rook"));
		board[7][1] = new Square("B1", LIGHT, new Knight(LIGHT, "Knight"));
		board[7][2] = new Square("C1", DARK, new Bishop(LIGHT, "Bishop"));
		board[7][3] = new Square("D1", LIGHT, new Queen(LIGHT, "Queen"));
		board[7][4] = new Square("E1", DARK, new King(LIGHT, "King"));
		board[7][5] = new Square("F1", LIGHT, new Bishop(LIGHT, "Bishop"));
		board[7][6] = new Square("G1", DARK, new Knight(LIGHT, "Knight"));
		board[7][7] = new Square("H1", LIGHT, new Rook(LIGHT, "Rook"));

	}

	public void enableSquares(Moves moves)
	{
		Pair p;
		int size;
		int i;

		size = moves.getSize();

		for(i = 0; i < size; i++)
		{
			p = moves.getPair(i);

			board[p.getRow()][p.getCol()].enable();
		}
	}

	public Pair findKing(boolean kingColor)
	{
		Piece p;
		Pair ans = null;
		for (int row = 0; row < 8; row++) 
		{
			for (int col = 0; col < 8; col++) 
			{
				p = board[row][col].getPiece();
				if(p instanceof King && p.getColor() == kingColor)
				{
					ans = new Pair(row, col);
				}
			}
		}
		return ans;
	}


	public void disableAllSquares()
	{
		int i, j;
		
		for(i = 0; i < 8; i++)
			for(j = 0; j < 8; j++)
				board[i][j].disable();
	}

	public void highlightSquares(Moves moves)
	{
		Pair p;
		int size;
		int i;

		size = moves.getSize();

		for(i = 0; i < size; i++)
		{
			p = moves.getPair(i);

			board[p.getRow()][p.getCol()].setHighlighted(true);
		}
		
	}

	public void unhighlightAllSquares()
	{
		int i, j;

		for(i = 0; i < 8; i++)
			for(j = 0; j < 8; j++)
				board[i][j].setHighlighted(false);

	}

	public Square getSquare(Pair pair)
	{
		return board[pair.getRow()][pair.getCol()];
	}

	public Square getSquare(int row, int col)
	{
		return board[row][col];
	}

	public void enablePiecesByColor(boolean color)
	{
		Piece p;
		int i, j;

		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				p = board[i][j].getPiece();
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
	 * printBoard()
	 *
	 * THE FOLLOWING METHOD IS PRIMARILY FOR DEBUGGING PURPOSES
	 * Prints a text based version of the board! :)
	 *
	 */

	public void printBoard()
	{
		int row;
		int col;
		String pieceName;
		String pieceColStr;
		boolean pieceColBool;

		System.out.println("\n");
		
		for(row = 0; row < 8; row++)
		{
			for(col = 0; col < 8; col++)
			{
				
				if(board[row][col].getPiece() != null)
				{
					pieceName = board[row][col].getPiece().getName();
					pieceColBool = board[row][col].getPiece().getColor();
					
					if(pieceColBool == LIGHT)
						pieceColStr = "L";
					else
						pieceColStr = "D";
				}
				else
				{
					pieceName = "NO";
					pieceColStr = "NO";
				}


				System.out.printf("(%s, %s)    ", pieceName, pieceColStr);
			}

			System.out.printf("\n");
		}


		System.out.println("\n");
	}


    	/*
         * getValidMoves()
	 *
         * the purpose of this method is to validate the moves of the piece at a Square
	 *
         * DEBUG: could not develop a condition in which moves for rooks, bishops, and the queen
         * that would invalidate moves in a space after which passing through a player owned piece
         * (i.e. a queen at the beginning of the game would have valid moves)
         */

	public Moves getValidMoves(Pair location) 
	{
		Piece p = getSquare(location).getPiece();
		Moves validMoves;
		Moves allMoves;
		Pair currentPair;
		Piece currentPiece;
		int index;
		int c;
		int r;

		validMoves = new Moves();

		r = location.getRow();
		c = location.getCol();

		// No piece on this square
		if(p == null) 
		{
			return null;
		}
		
		allMoves = p.move(location);

		for(int i = 0; i < allMoves.getSize(); i++) 
		{
			currentPair = allMoves.getPair(i);

			currentPiece = getSquare(currentPair).getPiece();
			
			if(currentPiece != null)
			{

				if(p instanceof Rook || p instanceof Queen) // The piece we're working with is a Rook or Queen, remove unreachable moves
				{
					if(location.getRow() == currentPair.getRow()) // The Rook/Queen is on the same row as another piece
					{
						if(location.getCol() < currentPair.getCol())
						{
							for(int col = 1; (col + currentPair.getCol()) < 8; col++)
							{
								index = allMoves.findPair(currentPair.getRow(), col + currentPair.getCol());

								if(index != -1)
									allMoves.removeMove(index);
							}
						}
						else
						{
							for(int col = 1; (currentPair.getCol() - col) >= 0; col++)
							{
								index = allMoves.findPair(currentPair.getRow(), currentPair.getCol() - col);

								if(index != -1)
									allMoves.removeMove(index);
							}
						}
					}
					else if(location.getCol() == currentPair.getCol()) // The Rook/Queen is on the same column as another piece
					{
						if(location.getRow() < currentPair.getRow())
						{
							for(int row = 1; (currentPair.getRow() - row) >= 0; row++)
							{
								index = allMoves.findPair(currentPair.getRow() - row, currentPair.getCol());

								if(index != -1)
									allMoves.removeMove(index);
							}
						}
						else
						{
							for(int row = 1; (currentPair.getRow() + row) < 8; row++)
							{
								index = allMoves.findPair(currentPair.getRow() + row, currentPair.getCol());

								if(index != -1)
									allMoves.removeMove(index);
							}
						}
					}
				}
				if(p instanceof Bishop || p instanceof Queen)
				{

            			}

				if(p.getColor() == currentPiece.getColor())
				{
					allMoves.removeMove(i);
					i--;
				}
			}
		}
		return allMoves;
	}
}
