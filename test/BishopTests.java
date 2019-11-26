package test;

import board.Board;
import org.junit.Test;
import pieces.Bishop;
import pieces.Moves;
import pieces.Pair;
import pieces.Pawn;

import static constant.Colors.LIGHT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
 * BishopTests
 *
 * The purpose of this class is to use JUnit testing to test
 * the valid moves generated for a Bishop in different situations.
 */

public class BishopTests
{
    public void testBishopAlone()
    {
        Board board = new Board(true);
        Pair bishopLocation;
        Moves generatedMoves;

        bishopLocation = new Pair(7, 2);

        board.getSquare(bishopLocation).setPiece(new Bishop(LIGHT, "Bishop"));

        generatedMoves = board.getValidMoves(bishopLocation);

        assertTrue(generatedMoves.contains(new Pair(6, 1)));
        assertTrue(generatedMoves.contains(new Pair(5, 0)));
        assertTrue(generatedMoves.contains(new Pair(6, 3)));
        assertTrue(generatedMoves.contains(new Pair(5, 4)));
        assertTrue(generatedMoves.contains(new Pair(4, 5)));
        assertTrue(generatedMoves.contains(new Pair(3, 6)));
        assertTrue(generatedMoves.contains(new Pair(2, 7)));
    }
}
