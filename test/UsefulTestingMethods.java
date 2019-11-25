package test;

import board.Board;
import org.junit.Before;
import org.junit.Test;

import pieces.King;
import pieces.Moves;
import pieces.Pair;

import static org.junit.Assert.*;

public class UsefulTestingMethods
{

//	public static void checkIfPairInMoves(Moves moves, Pair pair)
//	{
//		int movesSize;
//
//		movesSize = moves.getSize();
//
//		for(int i = 0; i < movesSize; i++)
//		{
//			if((pair.getRow() == moves.getPair(i).getRow()) && (pair.getCol() == moves.getPair(i).getCol()))
//				return;
//		}
//
//		fail("Pair (" + pair.getRow() + "," + pair.getCol() + ") not found in the given Moves object!");
//	}


	@Test
	public void testKing(){
		Board testBoard = new Board(true);
		testBoard.getSquare(4,4).setPiece(new King(true, "Name"));
		Moves m = new Moves();
		Moves kingMoves = testBoard.getValidMoves(new Pair(4,4));
		Pair p = new Pair (3,5);
		assertTrue(kingMoves.contains(p));
		p = new Pair (5,3);
		assertTrue(kingMoves.contains(p));
		p = new Pair (5,5);
		assertTrue(kingMoves.contains(p));
		p = new Pair (3,3);
		assertTrue(kingMoves.contains(p));
		p = new Pair (4,3);
		assertTrue(kingMoves.contains(p));
		p = new Pair (4,5);
		assertTrue(kingMoves.contains(p));
		p = new Pair (3,4);
		assertTrue(kingMoves.contains(p));
		p = new Pair (5,4);
		assertTrue(kingMoves.contains(p));

	}

}

