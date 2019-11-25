package test;

import board.Board;
import org.junit.Test;
import pieces.King;
import pieces.Moves;
import pieces.Pair;
import pieces.Rook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
    This class tests the getValidMoves method in Board.java for the King piece
 */

public class KingTests {

    /*
        This test makes sure that a king can move to all empty squares around it
     */
    @Test
    public void testKingNothingAround(){
        Board testBoard = new Board(true);
        testBoard.getSquare(4,4).setPiece(new King(true, "Name"));
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
        assertEquals(8, kingMoves.getSize());
    }

    /*
        This test makes sure that the king cannot move off of the board
     */
    @Test
    public void testKingInCorner(){
        Board testBoard = new Board(true);
        testBoard.getSquare(0,0).setPiece(new King(true, "Name"));

        Moves kingMoves = testBoard.getValidMoves(new Pair(0,0));
        Pair p = new Pair (0,1);
        assertTrue(kingMoves.contains(p));
        p = new Pair (1,0);
        assertTrue(kingMoves.contains(p));
        p = new Pair (1,1);
        assertTrue(kingMoves.contains(p));
        assertEquals(3, kingMoves.getSize());

    }

    /*
        This makes sure that the king cannot move if it is surrounded by pieces of its own color
     */
    @Test
    public void testKingSurrounded(){
        Board testBoard = new Board(true);
        testBoard.getSquare(4,4).setPiece(new King(true, "Name"));

        Rook piece = new Rook(true, "Test");

        Pair p = new Pair (3,5);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (5,3);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (5,5);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (3,3);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (4,3);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (4,5);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (3,4);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (5,4);
        testBoard.getSquare(p).setPiece(piece);

        Moves kingMoves = testBoard.getValidMoves(new Pair(4,4));
        assertEquals(0, kingMoves.getSize());
    }

    /*
        This test checks to see if the king can move to a location within its moveset that is
        occupied by the opposing color. The other 7 spots are the same color as the king.

     */
    @Test
    public void testKingSurroundedExcept1Cap(){
        Board testBoard = new Board(true);
        testBoard.getSquare(4,4).setPiece(new King(true, "Name"));

        Rook piece = new Rook(true, "Test");

        Pair p = new Pair (3,5);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (5,3);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (5,5);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (3,3);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (4,3);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (4,5);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (3,4);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (5,4);
        testBoard.getSquare(p).setPiece(new Rook(false, "Other"));

        Moves kingMoves = testBoard.getValidMoves(new Pair(4,4));
        assertEquals(1, kingMoves.getSize());
        assertTrue(kingMoves.contains(p));
    }


    /*
        Similar to the above test except there are 3 pieces of the opposing color.
        NOTE: This test class has nothing to do with check and checkmate
     */
    @Test
    public void testKingSurroundedExcept3Caps(){
        Board testBoard = new Board(true);
        testBoard.getSquare(4,4).setPiece(new King(true, "Name"));

        Rook piece = new Rook(true, "Test");

        Pair p = new Pair (3,5);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (5,3);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (5,5);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (3,3);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (4,3);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (4,5);

        piece = new Rook(false, "Other");
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (3,4);
        testBoard.getSquare(p).setPiece(piece);
        p = new Pair (5,4);
        testBoard.getSquare(p).setPiece(piece);
        Moves kingMoves = testBoard.getValidMoves(new Pair(4,4));
        assertEquals(3, kingMoves.getSize());
        assertTrue(kingMoves.contains(p));
        assertTrue(kingMoves.contains(new Pair(4, 5)));
        assertTrue(kingMoves.contains(new Pair(3, 4)));
    }
}
