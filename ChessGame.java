import board.*;
import graphics.*;
import pieces.*;
import player.*;

import static constant.Colors.LIGHT;
import static constant.Colors.DARK;

public class ChessGame {
	private Board board;
	private Player p1;
	private Player p2;
	private BoardGUI boardgui;

	public ChessGame() {
		board = new Board();
		p1 = new Player(LIGHT);
		p2 = new Player(DARK);
		boardgui = new BoardGUI();
	}

	public void run() {
		Pair lastButtonPressed;

		Board board = new Board();
		board.printBoard();

		// boardgui.display(board);
	}

	public void highlightMoves(Moves moves) {

	}

	public Pair findKing(boolean kingColor)
	{
		Piece p;
		Pair ans = null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				p = board.getSquare(i,j).getPiece();
				if(p instanceof King)
				{
					ans = new Pair(i, j);
				}
			}
		}
		return ans;
	}

	public boolean check(boolean kingColor)//the color parameter is the color king that we are checking for check
	{
		Piece p;
		boolean ans = false;
		Pair kingLoc = findKing(kingColor);

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				p = board.getSquare(i,j).getPiece();
				if(p == null)
				{
					continue;
				}
				else if(p.getColor() != kingColor)
				{//if the piece on this spot has a valid move to where the king is located, we go into check
					Moves m = board.getValidMovesFromSquare(board.getSquare(i,j));
					if(m.findPair(kingLoc.getRow(),kingLoc.getCol()) != -1)
					{
						ans = true;
					}
				}
			}
		}
		return ans;
	}

	public boolean checkMate(boolean kingColor)
	{
		Piece p;
		Square s;
		boolean ans = false;
		boolean tracker = true;
		Pair kingLoc = findKing(kingColor);
		Pair attackDest;
		Square kingSquare = board.getSquare(kingLoc.getRow(),kingLoc.getCol());
		Moves kingMoves = board.getValidMovesFromSquare(kingSquare);
		Moves attackMoves;
		//this will only go thru if the king is already in check
		if(this.check(kingColor))
		{
			if(kingMoves.getSize() == 0)//if the king cannot move and there is a valid move to reach it, checkmate
			{
				ans = true;
			}
			else
			{
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						s = board.getSquare(i,j);
						p = s.getPiece();
						attackMoves = board.getValidMovesFromSquare(s);
						if(p == null)
						{
							continue;
						}//p is the piece again, loop thru and get all of the pieces that are the opposite color
                        //of the king
						else if(p.getColor() != kingColor)
						{
							for (int x = 0; x < attackMoves.getSize(); x++) {
								attackDest = attackMoves.getPair(x);
								if(kingMoves.findPair(attackDest.getRow(),attackDest.getCol()) != -1)//for the valid
								    //king moves, if the attacker can move to an available move, remove that move from
								    //the possible king moves
								{
                                    kingMoves.removeMove(new Pair(attackDest.getRow(),attackDest.getCol()));
								}
							}
						}
					}
				}
				if(kingMoves.getSize() == 0)//if all possible moves have been removed, we are in checkmate
                {
                    ans = true;
                }
			}
		}
		return ans;
	}

}
