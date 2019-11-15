package player;
import board.Square;
import graphics.BoardSpot;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static constant.Points.PAWN_VALUE;
import static constant.Points.ROOK_COST;
import static constant.Points.KNIGHT_COST;
import static constant.Points.BISHOP_COST;
import static constant.Points.QUEEN_COST;

public class Graveyard
{
	private ArrayList<Piece> graveyard;

	public Graveyard()
	{
		graveyard = new ArrayList<Piece>();
	}

	public void addToGraveyard(Piece p)
	{
		graveyard.add(p);
	}

	public int getNumPieces()
	{
		return graveyard.size();
	}

	public Piece getPiece(int index)
	{
		if((index >= 0) && (index <= graveyard.size()))
			return graveyard.get(index);

		return null;
	}

	public void removePiece(int index)
	{
		if((index >= 0) && (index <= graveyard.size()))
			graveyard.remove(index);
	}

	public Square[] getPieces(Player player)
	{
//		Piece[] capturedPieces = new Piece[numPieces];
//		for(int i = 0; i < numPieces; i++){
//			capturedPieces[i]= new Square();
//			capturedPieces[i].setBackground(getPlayerColor(player));
//			capturedPieces[i].setEnabled(getPieceValue(graveyard.get(i)) <= player.getNumPoints()
//					? true : false);
//		}
		return null;
	}

	public static int getCost(Piece piece)
	{
		if(piece instanceof Rook)
			return ROOK_COST;
		else if(piece instanceof Knight)
			return KNIGHT_COST;
		else if(piece instanceof Bishop)
			return BISHOP_COST;
		else if(piece instanceof Queen)
			return QUEEN_COST;

		return 0;
	}

	private int getPieceValue(Piece piece)
	{
		int value = 0;
		String name = piece.getName();
		switch (name){
			case "Pawn":
				value = PAWN_VALUE;
				break;
			case "Rook":
				value = ROOK_COST;
				break;
			case "Knight":
				value = KNIGHT_COST;
				break;
			case "Bishop":
				value = BISHOP_COST;
				break;
			case "Queen":
				value = QUEEN_COST;
				break;
			default:
				value = 0;
				break;
		}
		return value;
	}

	private void setBuyBackAvailability(JButton capturedPiece)
	{

	}

	private Color getPlayerColor(Player player)
	{
		return player.getPlayerNum().equals("Player 2") ? new Color(222, 184, 135)
				: new Color(180, 79, 20);
	}

	private BoardSpot getCapturedPiece(Piece piece)
	{
		JLabel pieceImage = new JLabel(piece.getUnicode());
		BoardSpot button = new BoardSpot(piece.getUnicode());
		return button;
	}



}
