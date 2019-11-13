package player;
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

	public JButton[] getPieces(Player player)
	{
		JButton[] capturedPieces = new JButton[numPieces];
		for(int i = 0; i < numPieces; i++){
			capturedPieces[i]= getCapturedPiece(graveyard.get(i));
			capturedPieces[i].setBackground(getPlayerColor(player));
		}
		return capturedPieces;
	}

	private Color getPlayerColor(Player player)
	{
		return player.getPlayerNum().equals("Player 1") ? new Color(222, 184, 135)
				: new Color(180, 79, 20);
	}

	private JButton getCapturedPiece(Piece piece)
	{
		JLabel pieceImage = new JLabel(piece.getUnicode());
		JButton button = new JButton(piece.getUnicode());
		button.setFont(new Font("Ariel", Font.PLAIN, 50));
		return button;
	}



}
