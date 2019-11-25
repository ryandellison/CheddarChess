import static org.junit.Assert.fail;
import org.junit.Test;

import pieces.Moves;
import pieces.Pair;

public class UsefulTestingMethods
{

	public static void checkIfPairInMoves(Moves moves, Pair pair)
	{
		int movesSize;

		movesSize = moves.getSize();

		for(int i = 0; i < movesSize; i++)
		{
			if((pair.getRow() == moves.getPair(i).getRow()) && (pair.getCol() == moves.getPair(i).getCol()))
				return;
		}

		fail("Pair (" + pair.getRow() + "," + pair.getCol() + ") not found in the given Moves object!");
	}

}

