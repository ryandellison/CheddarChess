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
    private Moves calculatedMoves;

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
    public void testLightPawnFirstMove()
    {
        board.getSquare(6,1).setPiece(testPawn);
        calculatedMoves = board.getValidMoves(new Pair(6,1));
        Pair[] actualMoves = {new Pair(5,1),new Pair(4,1)};
        for (Pair actualMove : actualMoves) {
            assertTrue(calculatedMoves.contains(actualMove));
        }
    }

    /**
     * Testing single move up the board and setting the first move to false
     */
    @Test
    public void testPawnSingleMove()
    {
        board.getSquare(5,2).setPiece(testPawn);
        testPawn.setFirstMoveToFalse();
        calculatedMoves = board.getValidMoves(new Pair(5,2));
        Pair destPair = new Pair(4,2);
        assertTrue(calculatedMoves.contains(destPair));
    }

    /**
     * Tests to make sure that only valid moves are returned when
     * there is a possibility of a capture
     */
    @Test
    public void testNumberOfValidMovesDuringCapture()
    {
        board.getSquare(4,3).setPiece(testPawn);
        board.getSquare(3,2).setPiece(new Pawn(false,"Pawn"));
        testPawn.setFirstMoveToFalse();
        calculatedMoves = board.getValidMoves(new Pair(4,3));
        assertEquals(2,calculatedMoves.getSize());
    }

    /**
     * Tests that the moves actually includes a pawn that can be captured
     */
    @Test
    public void testPawnBeingAbleToCapture()
    {
        Pawn pawnToCapture = new Pawn(false,"Pawn");
        board.getSquare(5,3).setPiece(testPawn);
        board.getSquare(4,2).setPiece(pawnToCapture);
        testPawn.setFirstMoveToFalse(); pawnToCapture.setFirstMoveToFalse();
        calculatedMoves = board.getValidMoves(new Pair(5,3));
        Pair[] actualMoves = {new Pair(4,2),new Pair(4,3)};
        for(Pair currentMove : actualMoves){
            assertTrue(calculatedMoves.contains(currentMove));
        }

    }

}

