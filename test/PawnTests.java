package test;

import board.Board;
import org.junit.Before;
import org.junit.Test;

import pieces.*;

import static org.junit.Assert.*;

/**
 * Collection of test cases for pawn
 */
public class PawnTests
{

    private Board board;
    private Pawn testPawn;

    @Before
    public void initializeBoard()
    {
        board = new Board(true);
        testPawn = new Pawn(true,"Pawn");
    }

    /**
     * Simple test case to see that pawn moves two spaces in the beginning
     */
    @Test
    public void testLightPawnMoves()
    {
        board.getSquare(6,1).setPiece(testPawn);
        Moves boardMoves = board.getValidMoves(new Pair(6,1));
        Pair[] actualMoves = {new Pair(5,1),new Pair(4,1)};
        for (Pair actualMove : actualMoves) {
            assertTrue(boardMoves.contains(actualMove));
        }
    }
}

