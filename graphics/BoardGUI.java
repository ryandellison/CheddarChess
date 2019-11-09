package graphics;


import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import java.awt.event.*;

import board.*;
import pieces.*;

import static constant.Colors.LIGHT;
import static constant.Colors.DARK;


public class BoardGUI extends JFrame implements ActionListener {

	private static final String LIGHT_QUEEN = "\u2654";
	private static final String LIGHT_BISHOP = "\u2658";
	private static final String LIGHT_KNIGHT = "\u2657";
	private static final String LIGHT_KING = "\u2655";
	private static final String LIGHT_PAWN = "\u2659";
	private static final String LIGHT_ROOK = "\u2656";

	private static final String DARK_QUEEN = "\u265A";
	private static final String DARK_BISHOP = "\u265D";
	private static final String DARK_KNIGHT = "\u265E";
	private static final String DARK_KING = "\u265B";
	private static final String DARK_PAWN = "\u265f";
	private static final String DARK_ROOK = "\u265C";

	private static final String EMPTY_PIECE = "";
	private BoardSpot[][] pieces;

	private Pair lastButtonPressed;

	private GridLayout gridLayout;

	public BoardGUI()
	{
		pieces = new BoardSpot[8][8];
		lastButtonPressed = null;
	}

	@SuppressWarnings("WeakerAccess")
	public void display(Board b)
	{
		setBoard(b);
		setTitle("Chess");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container contentPane = getContentPane();
		gridLayout = new GridLayout(8, 8);

		contentPane.setLayout(gridLayout);
		/*
		int row = -1;
		for (int i = 0; i < pieces.length; i++) {
			if(i % 8 == 0) row ++;
			pieces[i].set(i, row);
			contentPane.add(pieces[i]);
			pieces[i].setEnabled(!pieces[i].isEmpty());
		}
		*/

		int i, j;

		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				pieces[i][j].set(i, j);
				contentPane.add(pieces[i][j]);
				pieces[i][j].setEnabled(!pieces[i][j].isEmpty());
			}
		}

		setSize(600, 600);
		setVisible(true);
	}

	public void movePiece(int x, int y)
	{


	}

	public void actionPerformed(ActionEvent e)
	{
		int i, j;
	
		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				if(pieces[i][j] == e.getSource())
				{	
					lastButtonPressed = new Pair(i, j);
					j = 8;
					i = 8;
				}
			}
		}
	}

	public Pair getLastButtonPressed()
	{
		return lastButtonPressed;
	}

	public void setBoard(Board b)
	{
		int i, j;

		Piece currentPiece;
		boolean pieceColor;
		String pieceName;

		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				currentPiece = b.getSquare(i,j).getPiece();
				
				if(currentPiece != null)
				{
					pieceColor = currentPiece.getColor();
					pieceName = currentPiece.getName();

					if(pieceColor == LIGHT)
					{
						if(pieceName.equals("Pawn"))
							pieces[i][j] = new BoardSpot(LIGHT_PAWN);
						else if(pieceName.equals("Rook"))
							pieces[i][j] = new BoardSpot(LIGHT_ROOK);
						else if(pieceName.equals("Bishop"))
							pieces[i][j] = new BoardSpot(LIGHT_BISHOP);
						else if(pieceName.equals("Knight"))
							pieces[i][j] = new BoardSpot(LIGHT_KNIGHT);
						else if(pieceName.equals("Queen"))
							pieces[i][j] = new BoardSpot(LIGHT_QUEEN);
						else	pieces[i][j] = new BoardSpot(LIGHT_KING);
					}
					else
					{
						if(pieceName.equals("Pawn"))
							pieces[i][j] = new BoardSpot(DARK_PAWN);
						else if(pieceName.equals("Rook"))
							pieces[i][j] = new BoardSpot(DARK_ROOK);
						else if(pieceName.equals("Bishop"))
							pieces[i][j] = new BoardSpot(DARK_BISHOP);
						else if(pieceName.equals("Knight"))
							pieces[i][j] = new BoardSpot(DARK_KNIGHT);
						else if(pieceName.equals("Queen"))
							pieces[i][j] = new BoardSpot(DARK_QUEEN);
						else	pieces[i][j] = new BoardSpot(DARK_KING);

					}
				}
				else
				{
					pieces[i][j] = new BoardSpot(EMPTY_PIECE);
				}

				pieces[i][j].addActionListener(this);

			}

		}
	}


}
