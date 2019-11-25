package test;

import board.Board;
import org.junit.Test;
import pieces.Pawn;
import pieces.Moves;
import pieces.Pair;
import pieces.Rook;

import static org.junit.Assert.*;

/*
    This class tests the getValidMoves method in Board.java for the Rook piece
 */

public class RookTests {
    /*
        This test makes sure that the rook can move with nothing around it
     */
    @Test
    public void testRookInMiddleNothingAround(){
        Board testBoard = new Board(true);
        testBoard.getSquare(new Pair(4, 4)).setPiece(new Rook(true, "Name"));

        Moves rookMoves = testBoard.getValidMoves(new Pair(4, 4));

        assertTrue(rookMoves.contains(new Pair(4,0)));
        assertTrue(rookMoves.contains(new Pair(4,1)));
        assertTrue(rookMoves.contains(new Pair(4,2)));
        assertTrue(rookMoves.contains(new Pair(4,3)));
        assertTrue(rookMoves.contains(new Pair(4,5)));
        assertTrue(rookMoves.contains(new Pair(4,6)));
        assertTrue(rookMoves.contains(new Pair(4,7)));
        assertTrue(rookMoves.contains(new Pair(0,4)));
        assertTrue(rookMoves.contains(new Pair(1,4)));
        assertTrue(rookMoves.contains(new Pair(2,4)));
        assertTrue(rookMoves.contains(new Pair(3,4)));
        assertTrue(rookMoves.contains(new Pair(5,4)));
        assertTrue(rookMoves.contains(new Pair(6,4)));
        assertTrue(rookMoves.contains(new Pair(7,4)));
        assertEquals(14, rookMoves.getSize());
    }

    /*
        This test makes sure that the rook cannot jump pieces if it is in the corner
     */
    @Test
    public void testRookInCornerSurrounded(){
        Board testBoard = new Board(true);
        testBoard.getSquare(new Pair(0,0)).setPiece(new Rook(true,"Name"));
        testBoard.getSquare(new Pair(0,1)).setPiece(new Pawn(true,"Block"));
        testBoard.getSquare(new Pair(1,0)).setPiece(new Pawn(true,"Block"));

        Moves rookMoves = testBoard.getValidMoves(new Pair(0,0));
        assertEquals(0,rookMoves.getSize());
    }

    /*
        This test makes sure that the rook cannot jump pieces if it is in the corner but can
        capture pieces of the opposing color
     */
    @Test
    public void testRookInCornerSurroundedOtherColor(){
        Board testBoard = new Board(true);
        testBoard.getSquare(new Pair(0,0)).setPiece(new Rook(true,"Name"));
        testBoard.getSquare(new Pair(0,1)).setPiece(new Pawn(false,"Block"));
        testBoard.getSquare(new Pair(1,0)).setPiece(new Pawn(false,"Block"));

        Moves rookMoves = testBoard.getValidMoves(new Pair(0,0));
        assertEquals(2,rookMoves.getSize());
        assertTrue(rookMoves.contains(new Pair(0,1)));
        assertTrue(rookMoves.contains(new Pair(1,0)));
    }

    /*
        This test makes sure that the rook can move from one corner to the other 2 corresponding
        corners
     */
    @Test
    public void testRookInCorner(){
        Board testBoard = new Board(true);
        testBoard.getSquare(new Pair(0,0)).setPiece(new Rook(true,"Name"));

        Moves rookMoves = testBoard.getValidMoves(new Pair(0,0));
        assertEquals(14,rookMoves.getSize());
        assertTrue(rookMoves.contains(new Pair(0,7)));
        assertTrue(rookMoves.contains(new Pair(7,0)));
    }

    /*
        This test makes sure that the rook can capture pieces of the opposing color but not jump
        over them when they are not right next to it
     */
    @Test
    public void testRookCapture(){
        Board testBoard = new Board(true);
        testBoard.getSquare(new Pair(0,0)).setPiece(new Rook(true,"Name"));
        testBoard.getSquare(new Pair(0,4)).setPiece(new Pawn(false,"other"));
        testBoard.getSquare(new Pair(1,0)).setPiece(new Pawn(true,"other"));

        Moves rookMoves = testBoard.getValidMoves(new Pair(0, 0));

        assertEquals(4, rookMoves.getSize());
        assertTrue(rookMoves.contains(new Pair(0, 4)));
        assertTrue(rookMoves.contains(new Pair(0, 2)));
        assertFalse(rookMoves.contains(new Pair(1,0)));
    }
}
