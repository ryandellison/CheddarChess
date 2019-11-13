package player;
import pieces.*;
import java.util.ArrayList;

public class Graveyard
{
	public final static int PAWN_VALUE = 1;
	public final static int ROOK_VALUE = 2;
	public final static int KNIGHT_VALUE = 3;
	public final static int BISHOP_VALUE = 4;
	public final static int QUEEN_VALUE = 8;

	private ArrayList<Piece> graveyard;

	public Graveyard()
	{
		graveyard = new ArrayList<Piece>();
	}

	public void addToGraveyard(Piece p)
	{
		graveyard.add(p);
	}

}
