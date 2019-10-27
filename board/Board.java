import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;


public class Board extends JFrame {

	private BoardSpot[] pieces = new BoardSpot[] {

			new BoardSpot("\u2656"), new BoardSpot("\u2658"), new BoardSpot("\u2657"),
			new BoardSpot("\u2655"), new BoardSpot("\u2654"), new BoardSpot("\u2657"),
			new BoardSpot("\u2658"), new BoardSpot("\u2656"), new BoardSpot("\u2659"),
			new BoardSpot("\u2659"), new BoardSpot("\u2659"), new BoardSpot("\u2659"),
			new BoardSpot("\u2659"), new BoardSpot("\u2659"), new BoardSpot("\u2659"),
			new  BoardSpot("\u2659"),

			new BoardSpot(""), new BoardSpot(""), new BoardSpot(""),
			new BoardSpot(""), new BoardSpot(""), new BoardSpot(""),
			new BoardSpot(""), new BoardSpot(""), new BoardSpot(""),
			new BoardSpot(""), new BoardSpot(""), new BoardSpot(""),
			new BoardSpot(""), new BoardSpot(""), new BoardSpot(""),
			new BoardSpot(""), new BoardSpot(""), new BoardSpot(""),
			new BoardSpot(""), new BoardSpot(""), new BoardSpot(""),
			new BoardSpot(""), new BoardSpot(""), new BoardSpot(""),
			new BoardSpot(""), new BoardSpot(""), new BoardSpot(""),
			new BoardSpot(""), new BoardSpot(""), new BoardSpot(""),
			new BoardSpot(""), new BoardSpot(""),

			new BoardSpot("\u265F"), new BoardSpot("\u265F"), new BoardSpot("\u265F"),
			new BoardSpot("\u265F"), new BoardSpot("\u265F"), new BoardSpot("\u265F"),
			new BoardSpot("\u265F"), new BoardSpot("\u265F"), new BoardSpot("\u265C"),
			new BoardSpot("\u265E"), new BoardSpot("\u265D"), new BoardSpot("\u265B"),
			new BoardSpot("\u265A"), new BoardSpot("\u265D"), new BoardSpot("\u265E"),
			new BoardSpot("\u265C")
	};

	@SuppressWarnings("WeakerAccess")
	public void display()
	{
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

	public static void main(String[] args)
	{
		new Board().display();
	}
}
