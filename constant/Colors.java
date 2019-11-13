/*
 * Colors
 *
 * This class contains constants that are used to determine
 * whether or not a piece or space is light or dark. We determine
 * this using booleans, and thus to keep it consistent we have
 * these constants that are used often throughout the code.
 *
 */

package constant;

import java.awt.*;

public final class Colors
{
	public final static boolean LIGHT = true;
	public final static boolean DARK = false;

	public static final Color LIGHT_SPOT = new Color(222, 184, 135);
	public static final Color DARK_SPOT = new Color(139, 69, 19);
	public static final Color HIGHLIGHTED_SPOT = new Color(50, 205, 50);
}

