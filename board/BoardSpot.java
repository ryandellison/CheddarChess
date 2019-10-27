import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;


@SuppressWarnings("WeakerAccess")
public class BoardSpot extends JButton {

	private Font font;
	private Color lightSpot,darkSpot;
	private boolean isEmpty;

	BoardSpot(String piece)
	{
		super(piece);
		isEmpty = piece.isEmpty(); // refers to if the spot has a piece in it or not.
		font = new Font("Ariel", Font.PLAIN, 35);
		lightSpot = new Color(222, 184, 135);
		darkSpot  = new Color(139, 69, 19);
	}

	void set(int index, int row)
	{
		setFont(font);
		setOpaque(true);
		setBackground((index+row)%2 == 0 ? darkSpot : lightSpot);
		setHorizontalAlignment(SwingConstants.CENTER );
	}

	boolean isEmpty()
	{
		return isEmpty;
	}

}