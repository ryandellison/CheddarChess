package graphics;


import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import java.awt.event.*;

import board.*;
import pieces.*;
import player.*;

import static constant.Colors.LIGHT;
import static constant.Colors.DARK;


public class BoardGUI extends JFrame implements ActionListener {
	
	// UNICODE CONSTANTS FOR CHESS PIECES
	private static final String LIGHT_QUEEN = "\u2654";
	private static final String LIGHT_BISHOP = "\u2657";
	private static final String LIGHT_KNIGHT = "\u2658";
	private static final String LIGHT_KING = "\u2655";
	private static final String LIGHT_PAWN = "\u2659";
	private static final String LIGHT_ROOK = "\u2656";

	private static final String DARK_QUEEN = "\u265A";
	private static final String DARK_BISHOP = "\u265D";
	private static final String DARK_KNIGHT = "\u265E";
	private static final String DARK_KING = "\u265B";
	private static final String DARK_PAWN = "\u265f";
	private static final String DARK_ROOK = "\u265C";

	private static final int SIZE = 800;


	// CONSTANTS AND VARIABLES FOR GUI
	private static final String EMPTY_PIECE = "";
	private BoardSpot[][] boardSpots;
	private GridLayout gridLayout;

	private Board board;
	private Player player1;
	private Player player2;
	private boolean currentPlayer;

	public BoardGUI()
	{
		boardSpots = new BoardSpot[8][8];
		currentPlayer = LIGHT;

		setTitle("JChess");
		setSize(SIZE, SIZE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void run()
	{
		board = new Board();

		board.enablePiecesByColor(currentPlayer);

		display();
	}

	@SuppressWarnings("WeakerAccess")
	public void display()
	{
		int i, j;
		Container contentPane;
		
		setVisible(false);
		setBoardSpots();

		
		contentPane = getContentPane();
		gridLayout = new GridLayout(8, 8);

		contentPane.setLayout(gridLayout);

		contentPane.removeAll();

		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				contentPane.add(boardSpots[i][j]);
			}
		}

		setVisible(true);
	}



	public void movePiece(int x, int y)
	{


	}

	public void actionPerformed(ActionEvent e)
	{
		int i, j;
		
		System.out.println("hi!");

		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				if(boardSpots[i][j] == e.getSource())
				{	
					handleTurn(new Pair(i, j));
					j = 8;
					i = 8;
				}
			}
		}

		
	}

	public void handleTurn(Pair start)
	{
		int row;
		int col;

		row = start.getRow();
		col = start.getCol();

		board.highlightSquares(board.getSquare(row, col).getPiece().move(start));

		display();
	}

	public void setBoardSpots()
	{
		int i, j;

		Piece currentPiece;
		boolean pieceColor;
		String pieceName;

		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				currentPiece = board.getSquare(i,j).getPiece();
				
				if(currentPiece != null)
				{
					pieceColor = currentPiece.getColor();
					pieceName = currentPiece.getName();

					if(pieceColor == LIGHT)
					{
						if(pieceName.equals("Pawn"))
							boardSpots[i][j] = new BoardSpot(LIGHT_PAWN);
						else if(pieceName.equals("Rook"))
							boardSpots[i][j] = new BoardSpot(LIGHT_ROOK);
						else if(pieceName.equals("Bishop"))
							boardSpots[i][j] = new BoardSpot(LIGHT_BISHOP);
						else if(pieceName.equals("Knight"))
							boardSpots[i][j] = new BoardSpot(LIGHT_KNIGHT);
						else if(pieceName.equals("Queen"))
							boardSpots[i][j] = new BoardSpot(LIGHT_QUEEN);
						else	boardSpots[i][j] = new BoardSpot(LIGHT_KING);
					}
					else
					{
						if(pieceName.equals("Pawn"))
							boardSpots[i][j] = new BoardSpot(DARK_PAWN);
						else if(pieceName.equals("Rook"))
							boardSpots[i][j] = new BoardSpot(DARK_ROOK);
						else if(pieceName.equals("Bishop"))
							boardSpots[i][j] = new BoardSpot(DARK_BISHOP);
						else if(pieceName.equals("Knight"))
							boardSpots[i][j] = new BoardSpot(DARK_KNIGHT);
						else if(pieceName.equals("Queen"))
							boardSpots[i][j] = new BoardSpot(DARK_QUEEN);
						else	boardSpots[i][j] = new BoardSpot(DARK_KING);

					}
				}
				else
				{
					boardSpots[i][j] = new BoardSpot(EMPTY_PIECE);
				}

				boardSpots[i][j].addActionListener(this);

				if(board.getSquare(i,j).getEnabled())
					boardSpots[i][j].setEnabled(true);
				else
					boardSpots[i][j].setEnabled(false);

				if(board.getSquare(i,j).getHighlighted())
					boardSpots[i][j].set(i, j, true);
				else
					boardSpots[i][j].set(i, j, false);

			}

		}
	}


}
