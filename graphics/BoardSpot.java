package graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import static constant.Colors.LIGHT;
import static constant.Colors.DARK;


@SuppressWarnings("WeakerAccess")
public class BoardSpot extends JButton {

	private static final Color LIGHT_SPOT = new Color(222, 184, 135);
	private static final Color DARK_SPOT = new Color(139, 69, 19);
	private static final Color HIGHLIGHTED_SPOT = new Color(50, 205, 50);

	private Font font;
	private boolean isEmpty;
	private String piece;
	private boolean enabled;

	public BoardSpot(String piece)
	{
		super(piece);
		this.piece = piece;
		isEmpty = piece.isEmpty(); // refers to if the spot has a piece in it or not.
		font = new Font("Ariel", Font.PLAIN, 50);

		enabled = false;

	}

	public BoardSpot(String piece, int fontSize)
	{
		super(piece);
		this.piece = piece;
		isEmpty = piece.isEmpty();
		font = new Font("Ariel", Font.PLAIN,fontSize);

		enabled = true;
	}

	public void set(int index, int row, boolean highlight)
	{
		setFont(font);
		setOpaque(true);
		if(highlight)
			setBackground(HIGHLIGHTED_SPOT);
		else
			setBackground((index+row) % 2 == 0 ? DARK_SPOT : LIGHT_SPOT);
		setHorizontalAlignment(SwingConstants.CENTER );
	}

	public void highlight()
	{
		setBackground(HIGHLIGHTED_SPOT);
	}

	boolean isEmpty()
	{
		return isEmpty;
	}

}
