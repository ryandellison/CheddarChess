package test;

import board.Board;
import org.junit.Before;
import org.junit.Test;

import pieces.Moves;
import pieces.Pair;
import pieces.Queen;

import static org.junit.Assert.*;

/**
 * Collection of test cases for queen
 */
public class QueenTests
{
    private Board board;
    private Queen testPawn;

    @Before
    public void initializeBoard()
    {
        board = new Board(true);
        testPawn = new Queen(true,"Queen");
    }

}

