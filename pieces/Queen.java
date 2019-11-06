package pieces;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece
{

	public Queen(boolean color, String name)
	{
		super(color,name);
	}
	@Override
	public Set<Pair> move(Pair coord)
	{
		Set<Pair>possibleSpots = new HashSet<>();
		int x = coord.getX();
		int y = coord.getY();
		for(int i = -7; i < 7; i++){//bishop moves
			possibleSpots.add(new Pair(x + i, y + i));
		}
		//rook moves
		for(int i = -7; i < 7; i++){
		possibleSpots.add(new Pair(x + i, y));
	}
		for(int i = -7; i < 7; i++){
			possibleSpots.add(new Pair(x, y+i));
		}
		return possibleSpots;

	}
}