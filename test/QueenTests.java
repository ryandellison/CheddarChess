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

    /**
     * Possible moves from the bottom left corner
     */
    @Test
    public void testQueenBottomRightCornerEdgeMoves()
    {
        board.getSquare(7,0).setPiece(lightQueen);
        board.getSquare(6,1).setPiece(new Pawn(true,"Pawn"));
        board.getSquare(7,1).setPiece(new Knight(true,"Knight"));

        calculatedMoves = board.getValidMoves(new Pair(7,0));
        assertEquals(7,calculatedMoves.getSize());

        for(int i = 6; i > -1; i--)
            assertTrue(calculatedMoves.contains(new Pair(i,0))); // straight
    }

    /**
     * All possible moves of the queen from the center
     */
    @Test
    public void testQueenCenter()
    {
        int j = 8;
        board.getSquare(4,3).setPiece(darkQueen);

        calculatedMoves = board.getValidMoves(new Pair(4,3));

        assertEquals(27,calculatedMoves.getSize());

        for(int i = 0; i < 8; i++){
            if(i != 4)
                assertTrue(calculatedMoves.contains(new Pair(i,3))); // up down
        }for(int i = 0; i < 8; i++){
        if(i != 3)
            assertTrue(calculatedMoves.contains(new Pair(4,i))); // left to right
    }for(int i = 0; i < 8; i++){
        if(i != 4 && j != 4)
            assertTrue(calculatedMoves.contains(new Pair(i,--j))); // diag tr to bl
    }
        j = -1;
        for(int i = 1; i < 8; i++){
            if(i != 4 && j != 3)
                assertTrue(calculatedMoves.contains(new Pair(i,++j))); // diag tl to br
        }

    }

    /**
     * Tests queen in the corner with available pieces that can be captured
     */
    @Test
    public void testQueenWithPiecesInMiddle()
    {
        int j = 8;
        board.getSquare(0,0).setPiece(darkQueen);
        board.getSquare(3,3).setPiece(new Rook(true,"Rook"));
        board.getSquare(6,0).setPiece(new Knight(true,"Knight"));
        board.getSquare(0,5).setPiece(new Bishop(true,"Bishop"));


        calculatedMoves = board.getValidMoves(new Pair(0,0));

        assertEquals(14,calculatedMoves.getSize());

        for(int i = 1; i < 7; i++) {
            assertTrue(calculatedMoves.contains(new Pair(i, 0))); //  straight down no pieces
        }
        for(int i = 1; i < 6;i++) {
            assertTrue(calculatedMoves.contains(new Pair(0, i))); // across with a piece
        }
        for(int i = 1; i < 4; i++) {
            assertTrue(calculatedMoves.contains(new Pair(i, i))); // diag with a piece
        }
    }

    /**
     * Test queen going diagonal left and right only from starting position
     */
    @Test
    public void testQueenMoves()
    {
        board.getSquare(0,2).setPiece(new Bishop(false,"Bishop")); // left of queen
        board.getSquare(0,3).setPiece(darkQueen);
        board.getSquare(0,4).setPiece(new King(false,"King")); // right of queen
        board.getSquare(1,3).setPiece(new Pawn(false,"Pawn")); // front of queen

        calculatedMoves = board.getValidMoves(new Pair(0,3));
        assertEquals(7,calculatedMoves.getSize());
        int j = 3;
        for(int i = 1; i < 4; i++){ // diag left
            assertTrue(calculatedMoves.contains(new Pair(i,--j)));
        }
        j = 3;
        for(int i = 1; i < 4; i++){ // diag right
            assertTrue(calculatedMoves.contains(new Pair(i,++j)));
        }

    }

}

