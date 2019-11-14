import graphics.BoardGUI;
import org.junit.Before;
import org.junit.Test;
import pieces.*;

import static org.junit.Assert.*;

public class JChessTest {

    Piece bishopTest = new Bishop(false, "Bishop");
    Moves moves= new Moves();
    @Test
    public void getValidValidMovesTest(){
        for (int i = 1; i < 8; i++) {
            moves.addMove(new Pair(i, i));
        }
        assertEquals(moves, getValidMoves(bishopTest.move(new Pair(0,0))));
    }

}