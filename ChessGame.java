import board.*;

import static constant.Colors.LIGHT;
import static constant.Colors.DARK;

public class ChessGame
{
	private Board board;
	private Player p1;
	private Player p2;

	public ChessGame()
	{
		board = new Board();
		p1 = new Player(LIGHT);
		p2 = new Player(DARK);

	}

	public void run()
	{
		Board board = new Board();
		board.printBoard();
	}

}
