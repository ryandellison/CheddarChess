import board.*;
import graphics.*;
import pieces.*;

import static constant.Colors.LIGHT;
import static constant.Colors.DARK;

public class ChessGame
{
	private Board board;
	private Player p1;
	private Player p2;
	private BoardGui boardgui;

	public ChessGame()
	{
		board = new Board();
		p1 = new Player(LIGHT);
		p2 = new Player(DARK);
		boardgui = new BoardGui();
	}

	public void run()
	{
		Board board = new Board();
		board.printBoard();

		boardgui.display(board);
	}

	public void highlightMoves(Moves moves)
	{

	}

}
