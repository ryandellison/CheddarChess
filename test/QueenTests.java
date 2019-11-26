package test;

import board.Board;
import org.junit.Before;
import org.junit.Test;

import pieces.*;

import static org.junit.Assert.*;

/**
 * Collection of test cases for queen
 */
public class QueenTests
{
    private Board board;
    private Queen lightQueen;
    private Queen darkQueen;
    private Moves calculatedMoves;
    private Pair[] actualMoves;

    @Before
    public void initializeBoard()
    {
        board = new Board(true);
        lightQueen= new Queen(true,"Queen");
        darkQueen= new Queen(false,"Queen");
    }

    /**
     * Tests that queen in starting spot should have no available moves
     */
    @Test
    public void testLightQueenStartingMoves()
    {
        board.getSquare(7,2).setPiece(new Bishop(true,"Bishop"));
        board.getSquare(7,3).setPiece(lightQueen);
        board.getSquare(7,4).setPiece(new King(true,"King"));

        board.getSquare(6,2).setPiece(new Pawn(true,"Pawn"));
        board.getSquare(6,3).setPiece(new Pawn(true,"Pawn"));
        board.getSquare(6,4).setPiece(new Pawn(true,"Pawn"));

        calculatedMoves = board.getValidMoves(new Pair(7,3));
        assertEquals(0,calculatedMoves.getSize());

    }

}

