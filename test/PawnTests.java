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
    private Pawn lightPawn;
    private Pawn darkPawn;
    private Moves calculatedMoves;
    private Pair[] actualMoves;

    /**
     * Initializes a new board before runnning the test
     */
    @Before
    public void initializeBoard()
    {
        board = new Board(true);
        lightPawn = new Pawn(true,"Pawn");
        darkPawn = new Pawn(false,"Pawn");
    }

    /**
     * Simple test case to see that pawn moves two spaces in the beginning
     */
    @Test
    public void testLightPawnFirstMove()
    {
        board.getSquare(6,1).setPiece(lightPawn);
        calculatedMoves = board.getValidMoves(new Pair(6,1));
        assertEquals(2,calculatedMoves.getSize());
        actualMoves = new Pair[]{new Pair(5,1),new Pair(4,1)};
        for (Pair actualMove : actualMoves) {
            assertTrue(calculatedMoves.contains(actualMove));
        }
    }
/**
     * Simple test case to see that pawn moves two spaces in the beginning
     */
    @Test
    public void testDarkPawnFirstMove()
    {
        board.getSquare(1,1).setPiece(darkPawn);
        calculatedMoves = board.getValidMoves(new Pair(1,1));
        assertEquals(2,calculatedMoves.getSize());
        actualMoves = new Pair[]{new Pair(2,1),new Pair(3,1)};
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
        board.getSquare(5,2).setPiece(lightPawn);
        lightPawn.setFirstMoveToFalse();
        calculatedMoves = board.getValidMoves(new Pair(5,2));
        assertEquals(1,calculatedMoves.getSize());
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
        board.getSquare(4,3).setPiece(lightPawn);
        board.getSquare(3,2).setPiece(new Pawn(false,"Pawn"));
        lightPawn.setFirstMoveToFalse();
        calculatedMoves = board.getValidMoves(new Pair(4,3));
        assertEquals(2,calculatedMoves.getSize());
    }

    /**
     * Tests that the moves actually includes a pawn that can be captured
     */
    @Test
    public void testPawnCaptureOnePieceAvailable()
    {
        Pawn pawnToCapture = new Pawn(false,"Pawn");
        board.getSquare(5,3).setPiece(lightPawn);
        board.getSquare(4,2).setPiece(pawnToCapture);
        lightPawn.setFirstMoveToFalse(); pawnToCapture.setFirstMoveToFalse();
        calculatedMoves = board.getValidMoves(new Pair(5,3));
        assertEquals(2 ,calculatedMoves.getSize());
        actualMoves = new Pair[]{new Pair(4,2),new Pair(4,3)};
        for(Pair currentMove : actualMoves){
            assertTrue(calculatedMoves.contains(currentMove));
        }

    }

    /**
     * Tests that the moves actually includes two pawns that can be captured
     */
    @Test
    public void testPawnCaptureTwoPieceAvailable()
    {
        board.getSquare(5,3).setPiece(lightPawn);
        board.getSquare(4,2).setPiece(new Pawn(false,"Pawn"));
        board.getSquare(4,4).setPiece(new Knight(false,"Knight"));
        lightPawn.setFirstMoveToFalse();
        calculatedMoves = board.getValidMoves(new Pair(5,3));
        assertEquals(3,calculatedMoves.getSize());
        actualMoves = new Pair[]{new Pair(4,2),new Pair(4,3)
                ,new Pair(4,4)};
        for(Pair currentMove : actualMoves){
            assertTrue(calculatedMoves.contains(currentMove));
        }

    }

    /**
     * Tests the right edge of board with a piece available on the left and
     * a non capture-able piece in the front
     */
    @Test
    public void testPawnAtRightEdgeWithPieceAvailableForCapture()
    {
        board.getSquare(2,7).setPiece(darkPawn);
        board.getSquare(3,6).setPiece(new Queen(true,"Queen"));
        board.getSquare(3,7).setPiece(new Bishop(true,"Bishop"));
        darkPawn.setFirstMoveToFalse();
        calculatedMoves = board.getValidMoves(new Pair(2,7));
        assertEquals(1,calculatedMoves.getSize());
        actualMoves = new Pair[]{new Pair(3,6)};
        assertTrue(calculatedMoves.contains(actualMoves[0]));
    }
    /**
     * Tests the right edge of board with a piece available on the left and
     * a non capture-able piece in the front
     */
    @Test
    public void testPawnAtLeftEdgeWithPieceAvailableForCapture()
    {
        board.getSquare(2,0).setPiece(darkPawn);
        board.getSquare(3,1).setPiece(new Queen(true,"Queen"));
        board.getSquare(3,0).setPiece(new Bishop(true,"Bishop"));
        darkPawn.setFirstMoveToFalse();
        calculatedMoves = board.getValidMoves(new Pair(2,0));
        assertEquals(1,calculatedMoves.getSize());
        actualMoves = new Pair[]{new Pair(3,1)};
        assertTrue(calculatedMoves.contains(actualMoves[0]));
    }
}