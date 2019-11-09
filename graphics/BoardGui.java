package graphics;


import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;

import board.*;
import pieces.*;

import static constant.Colors.LIGHT;
import static constant.Colors.DARK;


public class BoardGui extends JFrame {

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
	private BoardSpot[] pieces;

	private GridLayout gridLayout;

	public BoardGui()
	{
		pieces = new BoardSpot[64];
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

		int row = -1;
		for (int i = 0; i < pieces.length; i++) {
			if(i % 8 == 0) row ++;
			pieces[i].set(i, row);
			contentPane.add(pieces[i]);
			pieces[i].setEnabled(!pieces[i].isEmpty());
		}

		setSize(600, 600);
		setVisible(true);
	}

	public void movePiece(int x, int y)
	{


	}

	public void setBoard(Board b)
	{
		int i, j;
		int x;

		Piece currentPiece;
		boolean pieceColor;
		String pieceName;
		x = 0;

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
							pieces[x] = new BoardSpot(LIGHT_PAWN);
						else if(pieceName.equals("Rook"))
							pieces[x] = new BoardSpot(LIGHT_ROOK);
						else if(pieceName.equals("Bishop"))
							pieces[x] = new BoardSpot(LIGHT_BISHOP);
						else if(pieceName.equals("Knight"))
							pieces[x] = new BoardSpot(LIGHT_KNIGHT);
						else if(pieceName.equals("Queen"))
							pieces[x] = new BoardSpot(LIGHT_QUEEN);
						else	pieces[x] = new BoardSpot(LIGHT_KING);
					}
					else
					{
						if(pieceName.equals("Pawn"))
							pieces[x] = new BoardSpot(DARK_PAWN);
						else if(pieceName.equals("Rook"))
							pieces[x] = new BoardSpot(DARK_ROOK);
						else if(pieceName.equals("Bishop"))
							pieces[x] = new BoardSpot(DARK_BISHOP);
						else if(pieceName.equals("Knight"))
							pieces[x] = new BoardSpot(DARK_KNIGHT);
						else if(pieceName.equals("Queen"))
							pieces[x] = new BoardSpot(DARK_QUEEN);
						else	pieces[x] = new BoardSpot(DARK_KING);

					}
				}
				else
				{
					pieces[x] = new BoardSpot(EMPTY_PIECE);
				}

				x++;

			}

		}
	}

	private void defaultBoardLayout()
	{
		pieces = new BoardSpot[] {

				new BoardSpot(LIGHT_ROOK), new BoardSpot(LIGHT_BISHOP), new BoardSpot(LIGHT_BISHOP),
				new BoardSpot(LIGHT_KING), new BoardSpot(LIGHT_QUEEN), new BoardSpot(LIGHT_BISHOP),
				new BoardSpot(LIGHT_KNIGHT), new BoardSpot(LIGHT_ROOK), new BoardSpot(LIGHT_PAWN),
				new BoardSpot(LIGHT_PAWN), new BoardSpot(LIGHT_PAWN), new BoardSpot(LIGHT_PAWN),
				new BoardSpot(LIGHT_PAWN), new BoardSpot(LIGHT_PAWN), new BoardSpot(LIGHT_PAWN),
				new  BoardSpot(LIGHT_PAWN),

				new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE),
				new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE),
				new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE),
				new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE),
				new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE),
				new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE),
				new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE),
				new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE),
				new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE),
				new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE),
				new BoardSpot(EMPTY_PIECE), new BoardSpot(EMPTY_PIECE),

				new BoardSpot(DARK_PAWN), new BoardSpot(DARK_PAWN), new BoardSpot(DARK_PAWN),
				new BoardSpot(DARK_PAWN), new BoardSpot(DARK_PAWN), new BoardSpot(DARK_PAWN),
				new BoardSpot(DARK_PAWN), new BoardSpot(DARK_PAWN), new BoardSpot(DARK_ROOK),
				new BoardSpot(DARK_KNIGHT), new BoardSpot(DARK_BISHOP), new BoardSpot(DARK_KING),
				new BoardSpot(DARK_QUEEN), new BoardSpot(DARK_BISHOP), new BoardSpot(DARK_KNIGHT),
				new BoardSpot(DARK_ROOK)
		};
	}

	public static void main(String[] args)
	{
		BoardGui boardGui = new BoardGui();


	}
}
