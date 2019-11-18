package pieces;

public class Queen extends Piece
{

	public Queen(boolean color, String name)
	{
		super(color,name);
	}

	@Override
	public Moves move(Pair coord)
	{
		Moves moves = new Moves();
		int row = coord.getRow();
		int col = coord.getCol();
		for(int i = -7; i < 8; i++){//bishop moves
			if(i != 0)

				moves.addMove(new Pair(row + i, col + i));
		}
		for(int i = -7; i < 8; i++){
			if(i != 0)

				moves.addMove(new Pair(row - i, col + i));
		}
		
		//rook moves
		for(int i = -7; i < 8; i++){
			if(i != 0)
				moves.addMove(new Pair(row + i, col));
		}
		
		for(int i = -7; i < 8; i++){
			if(i != 0)
				moves.addMove(new Pair(row, col+i));
		}
		moves.checkBounds();
		return moves;
	}

	public String getUnicode(){
		if(this.getColor() == true)
		{
			return "\u2654";
		}
		else
		{
			return "\u265A";
		}
	}
}
