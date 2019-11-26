package test;

import board.Board;
import org.junit.Test;
import org.junit.Before;
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

    private Board board;            // the board
    private Pair bishopLocation;    // the location of the bishop as a pair
    private Moves generatedMoves;   // the generated moves for that scenario

    /**
     * Initializes a new board before runnning the test
     */

    @Before
    public void initializeBoard()
    {
        board = new Board(true);
    }

    /**
     * Tests the bishop at its starting position on the board alone.
     */

    @Test
    public void testBishopAlone()
    {
        bishopLocation = new Pair(7, 2);

        // Put the bishop on the board
        board.getSquare(bishopLocation).setPiece(new Bishop(LIGHT, "Bishop"));

        // Generate the valid moves
        generatedMoves = board.getValidMoves(bishopLocation);

        assertTrue(generatedMoves.contains(new Pair(6, 1)));
        assertTrue(generatedMoves.contains(new Pair(5, 0)));
        assertTrue(generatedMoves.contains(new Pair(6, 3)));
        assertTrue(generatedMoves.contains(new Pair(5, 4)));
        assertTrue(generatedMoves.contains(new Pair(4, 5)));
        assertTrue(generatedMoves.contains(new Pair(3, 6)));
        assertTrue(generatedMoves.contains(new Pair(2, 7)));

        assertEquals(7, generatedMoves.getSize());
    }

    /**
     * Tests the bishop in its starting position but with a piece of the same
     * color in the way of its path.
     */

    @Test
    public void testBishopWithAllyInWay()
    {
        bishopLocation = new Pair(7, 2);

        // Put the bishop on the board
        board.getSquare(bishopLocation).setPiece(new Bishop(LIGHT, "Bishop"));

        // Put a LIGHT colored pawn in the way of the bishop's movement
        board.getSquare(new Pair(4, 5)).setPiece(new Pawn(LIGHT, "Pawn"));

        // Generate the valid moves
        generatedMoves = board.getValidMoves(bishopLocation);

        assertTrue(generatedMoves.contains(new Pair(6, 1)));
        assertTrue(generatedMoves.contains(new Pair(5, 0)));
        assertTrue(generatedMoves.contains(new Pair(6, 3)));
        assertTrue(generatedMoves.contains(new Pair(5, 4)));

        assertEquals(4, generatedMoves.getSize());
    }

    /**
     * Tests the bishop in its starting position but with a piece of the opposite
     * color in the way of its path.
     */

    @Test
    public void testBishopWithEnemyInWay()
    {
        bishopLocation = new Pair(7, 2);

        // Put the bishop on the board
        board.getSquare(bishopLocation).setPiece(new Bishop(LIGHT, "Bishop"));

        // Put a DARK colored pawn in the way of the bishop's movement
        board.getSquare(new Pair(4, 5)).setPiece(new Pawn(!LIGHT, "Pawn"));

        // Generate the valid moves
        generatedMoves = board.getValidMoves(bishopLocation);

        assertTrue(generatedMoves.contains(new Pair(6, 1)));
        assertTrue(generatedMoves.contains(new Pair(5, 0)));
        assertTrue(generatedMoves.contains(new Pair(6, 3)));
        assertTrue(generatedMoves.contains(new Pair(5, 4)));
        assertTrue(generatedMoves.contains(new Pair(4, 5)));

        assertEquals(5, generatedMoves.getSize());
    }

    /**
     * Tests the bishop in a location in which we can check its generations
     * for all directions.
     */

    @Test
    public void testBishopInAllDirections()
    {
        bishopLocation = new Pair(5, 4);

        // Place the bishop on the board
        board.getSquare(bishopLocation).setPiece(new Bishop(LIGHT, "Bishop"));

        // Generate its valid moves
        generatedMoves = board.getValidMoves(bishopLocation);

        assertTrue(generatedMoves.contains(new Pair(6, 3)));
        assertTrue(generatedMoves.contains(new Pair(7, 2)));

        assertTrue(generatedMoves.contains(new Pair(6, 5)));
        assertTrue(generatedMoves.contains(new Pair(7, 6)));

        assertTrue(generatedMoves.contains(new Pair(4, 3)));
        assertTrue(generatedMoves.contains(new Pair(3, 2)));
        assertTrue(generatedMoves.contains(new Pair(2, 1)));
        assertTrue(generatedMoves.contains(new Pair(1, 0)));

        assertTrue(generatedMoves.contains(new Pair(4, 5)));
        assertTrue(generatedMoves.contains(new Pair(3, 6)));
        assertTrue(generatedMoves.contains(new Pair(2, 7)));

        assertEquals(11, generatedMoves.getSize());
    }

    /**
     * Tests the bishop by surrounding it with allies, so it shouldn't have
     * any valid moves.
     */

    @Test
    public void testBishopSurroundedByAllies()
    {
        bishopLocation = new Pair(5, 4);

        // Put bishop on the board
        board.getSquare(bishopLocation).setPiece(new Bishop(LIGHT, "Bishop"));

        // Surround the bishop with ally pawns
        board.getSquare(new Pair(6, 3)).setPiece(new Pawn(LIGHT, "Pawn"));
        board.getSquare(new Pair(6, 5)).setPiece(new Pawn(LIGHT, "Pawn"));
        board.getSquare(new Pair(4, 3)).setPiece(new Pawn(LIGHT, "Pawn"));
        board.getSquare(new Pair(4, 5)).setPiece(new Pawn(LIGHT, "Pawn"));

        // Generate the valid moves
        generatedMoves = board.getValidMoves(bishopLocation);

        // The bishop should have no moves
        assertEquals(0, generatedMoves.getSize());
    }
}
