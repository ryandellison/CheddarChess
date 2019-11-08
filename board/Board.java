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

	public void fillBoard()
	{
		int row;
		int col;
		boolean alternator;

		alternator = true;

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
					alternator = false;
				}
				else
				{
					board[row][col] = new Square(letter + Integer.toString(6 - (row - 2)), DARK, null);
					alternator = true;
				}
			}

			alternator = (alternator) ? false : true;
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
		String alias;
		String squareColStr;
		boolean squareColBool;
		String pieceName;
		String pieceColStr;
		boolean pieceColBool;

		System.out.println("\n");
		
		for(row = 0; row < 8; row++)
		{
			for(col = 0; col < 8; col++)
			{
				alias = board[row][col].getAlias();
				squareColBool = board[row][col].getColor();
				
				if(board[row][col].getPiece() != null)
				{
					pieceName = board[row][col].getPiece().getName();
					pieceColBool = board[row][col].getPiece().getColor();
					
					if(pieceColBool == LIGHT)
						pieceColStr = "LIGHT";
					else
						pieceColStr = "DARK";
				}
				else
				{
					pieceName = "NONE";
					pieceColStr = "NONE";
				}

				if(squareColBool == LIGHT)
					squareColStr = "LIGHT";
				else
					squareColStr = "DARK";


				System.out.printf("(%s, %s, %s, %s)    ", alias, squareColStr, pieceName, pieceColStr);
			}

			System.out.printf("\n");
		}


		System.out.println("\n");
	}
    /*
        Moves getValidMovesFromSquare(Square s);

        the purpose of this method is to validate the moves of the piece at a Square

        DEBUG: could not develop a condition in which moves for rooks, bishops, and the queen
               that would invalidate moves in a space after which passing through a player owned piece
               (i.e. a queen at the beginning of the game would have valid moves)
     */
	public Moves getValidMovesFromSquare(Square s) {
		Piece p = s.getPiece();
		String alias = s.getAlias();
		Moves validMoves = new Moves();
		int r = -1;
		char row = alias.charAt(0);
		//for getting the row of the square
		switch (row) {
			case 'A':
				r = 0;
				break;
			case 'B':
				r = 1;
				break;
			case 'C':
				r = 2;
				break;
			case 'D':
				r = 3;
				break;
			case 'E':
				r = 4;
				break;
			case 'F':
				r = 5;
				break;
			case 'G':
				r = 6;
				break;
			case 'H':
				r = 7;
				break;
		}
		if (p == null) {
			return null;
		}
		int c = Character.getNumericValue(alias.charAt(1));
		Pair coord = new Pair(r, c);
		Moves allMoves = p.move(coord);
		allMoves.checkBounds();//gets the possible moves and makes sure that they are all in bounds
		for (int i = 0; i < allMoves.getSize(); i++) {
			Pair pa = allMoves.getPair(i);
			Piece other = this.board[pa.getX()][pa.getY()].getPiece();
			if (other == null) {//if the square is empty, we can move there
				validMoves.addMove(new Pair(pa.getX(), pa.getY()));
			} else if (other.getColor() && p.getColor()) {//if its the same color, continue
				continue;
			} else if (!(other.getColor() && p.getColor())) {//if its a different color, we can move there
				validMoves.addMove(new Pair(pa.getX(), pa.getY()));
			}
		}
		return validMoves;
	}
}
