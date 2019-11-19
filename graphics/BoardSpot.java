package graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import static constant.Colors.LIGHT;
import static constant.Colors.DARK;

/**
 * Class that represents the board spots as buttons
 */
@SuppressWarnings("WeakerAccess")
public class BoardSpot extends JButton {

	private static final Color LIGHT_SPOT = new Color(222, 184, 135);
	private static final Color DARK_SPOT = new Color(139, 69, 19);
	private static final Color HIGHLIGHTED_SPOT = new Color(50, 205, 50);

	private Font font;
	private boolean isEmpty;
	private String piece;

	/**
	 * Sets the spot with a piece
	 * @param piece Name of the piece
	 */
	public BoardSpot(String piece)
	{
		super(piece);
		this.piece = piece;
		isEmpty = piece.isEmpty(); // refers to if the spot has a piece in it or not.
		font = new Font("Sans Serif", Font.PLAIN, 50);
		setFont(font);

	}

	/**
	 * Based on the current spot, set the color of the tile
	 * @param index col value
	 * @param row row value
	 * @param highlight if this spot is a potential move
	 */
	public void set(int index, int row, boolean highlight)
	{
		setOpaque(true);
		if(highlight)
			setBackground(HIGHLIGHTED_SPOT); // highlight if potential move
		else
			setBackground((index+row) % 2 == 0 ? DARK_SPOT : LIGHT_SPOT); // Background of tile
		setHorizontalAlignment(SwingConstants.CENTER );
	}
// NOT USED ANYWHERE DELETE?
//	public void highlight()
//	{
//		setBackground(HIGHLIGHTED_SPOT);
//	}
//
//	boolean isEmpty()
//	{
//		return isEmpty;
//	}

}
