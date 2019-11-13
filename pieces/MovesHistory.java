package pieces;

import board.Board;
import board.Square;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * JChess
 *
 * @author Mani Shah
 * Date:  11/13/2019
 * Time:  14:28
 * @since 1.0
 */

public class MovesHistory
{
	private File logFile;
	private static final String filePath = "MovesLog.txt";
	private PrintWriter printWriter;
	private FileWriter fileWriter;
	private String currentMove;
	private Board board;
	private int turn;

	public MovesHistory(Board board)
	{
		this.board = board;
		currentMove = "";
		turn = 0;
		fileWriter = null;
		initialize(false);
		printWriter = new PrintWriter(fileWriter);
		writeIntro("\t\t\tPlayer 1             Player 2 \t\t\t\t\t");
	}

	private void initialize(boolean append)
	{
		try {
			fileWriter = new FileWriter(filePath,append);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeIntro(String message)
	{
		printWriter.println(message);
		closeFile();
	}

	public void write(Square source, Square destination, int turn)
	{
		initialize(true);
		printWriter = new PrintWriter(fileWriter);
		convertToMessage(source,destination, turn);
		printWriter.println(currentMove);
//		System.out.println("closing file in write");
		closeFile();
	}

	private void convertToMessage(Square source, Square destination, int turn)
	{
		String name1 = source.getPiece().getName() + source.getAlias();
		String name2 = destination.getPiece().getName() + destination.getAlias();
		currentMove = "Turn " + turn + "      "+name1 + "             " + name2;
	}

	public void closeFile()
	{
		printWriter.close();
	}


}
