import board.*;

public class ChessGame
{

	public void run()
	{
		Board board = new Board();
		board.printBoard();

		Player p1 = new Player(false);
		Player p2 = new Player(true);
	}

}
