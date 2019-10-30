package board;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;


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


	@SuppressWarnings("WeakerAccess")
	public void display()
	{
		defaultBoardLayout();
		setTitle("Chess");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container contentPane = getContentPane();
		GridLayout gridLayout = new GridLayout(8, 8);

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

	private void defaultBoardLayout()
	{
		pieces = new BoardSpot[] {

				new BoardSpot(LIGHT_ROOK), new BoardSpot(LIGHT_KNIGHT), new BoardSpot(LIGHT_BISHOP),
				new BoardSpot(LIGHT_KING), new BoardSpot(LIGHT_QUEEN), new BoardSpot(LIGHT_BISHOP),
				new BoardSpot(LIGHT_KNIGHT), new BoardSpot(LIGHT_BISHOP), new BoardSpot(LIGHT_PAWN),
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
		new BoardGui().display();
	}
}
