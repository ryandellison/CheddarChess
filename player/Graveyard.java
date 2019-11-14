package player;
import graphics.BoardSpot;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graveyard
{
	public final static int PAWN_VALUE = 1;
	public final static int ROOK_VALUE = 2;
	public final static int KNIGHT_VALUE = 3;
	public final static int BISHOP_VALUE = 4;
	public final static int QUEEN_VALUE = 8;

	private int numPieces = 0;
	private ArrayList<Piece> graveyard;

	public Graveyard()
	{
		graveyard = new ArrayList<Piece>();
	}

	public void addToGraveyard(Piece p)
	{
		graveyard.add(p);
		numPieces++;
	}

	public int getNumPieces()
	{
		return numPieces;
	}

	public BoardSpot[] getPieces(Player player)
	{
		BoardSpot[] capturedPieces = new BoardSpot[numPieces];
		for(int i = 0; i < numPieces; i++){
			capturedPieces[i]= getCapturedPiece(graveyard.get(i));
			capturedPieces[i].setBackground(getPlayerColor(player));
			capturedPieces[i].setEnabled(getPieceValue(graveyard.get(i)) <= player.getNumPoints()
					? true : false);
		}
		return capturedPieces;
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
				value = ROOK_VALUE;
				break;
			case "Knight":
				value = KNIGHT_VALUE;
				break;
			case "Bishop":
				value = BISHOP_VALUE;
				break;
			case "Queen":
				value = QUEEN_VALUE;
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
