/*
 * BoardGUI
 *
 * The purpose of this class is to represent the chess board GUI
 * and contain any necessary logic for GUI interaction with other
 * components.
 */

package graphics;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import board.*;
import pieces.*;
import player.*;

import static constant.Colors.LIGHT;
import static constant.Colors.DARK;

import static constant.Points.PAWN_VALUE;

@SuppressWarnings("Duplicates")
public class BoardGUI extends JFrame implements ActionListener 
{

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

	// CONSTANTS AND VARIABLES FOR GUI
	private static final String EMPTY_PIECE = "";
	private BoardSpot[][] boardSpots;
	private ArrayList<BoardSpot> lightGraveyardSpots;
	private ArrayList<BoardSpot> darkGraveyardSpots;
	private GridLayout gridLayout;
	private JPanel boardPanel;
	private JPanel playerOnePanel;
	private JPanel playerTwoPanel;
	private Container contentPane;
	private JMenu menu;

	private Board board;
	private Player lightPlayer;
	private Player darkPlayer;
	private boolean currentPlayer;
	private Pair sourceLocation;
	private Square destSquare1, destSquare2;
	private boolean currentlyMoving = false;
	private boolean isGameOver = false;
	private boolean isAlreadyLoaded = false;

	private GameState gameState;

	private boolean inCheck;
	private boolean inCheckMate;

	private boolean bringingBackTheDead;
	private Piece deadPiece;
	private int locationOfDead;

	public BoardGUI()
	{
		boardSpots = new BoardSpot[8][8];

		lightGraveyardSpots = new ArrayList<BoardSpot>();
		darkGraveyardSpots = new ArrayList<BoardSpot>();

		currentPlayer = LIGHT;
		sourceLocation = null;

		playerOnePanel = new JPanel();
		playerTwoPanel = new JPanel();

		setTitle("JChess - Light's Turn");
		setDimensions();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lightPlayer = new Player(LIGHT);
		darkPlayer = new Player(DARK);

		bringingBackTheDead = false;
		deadPiece = null;

		inCheck = false;
		inCheckMate = false;
	}

    /**
     * Constructor used for loading game
     * @param lightPlayer Object received from the saved game
     * @param darkPlayer Object received from the saved game
     * @param currentPlayer the current turn received from saved game
     */
	private BoardGUI(Player lightPlayer, Player darkPlayer, boolean currentPlayer )
	{
		boardSpots = new BoardSpot[8][8];

		lightGraveyardSpots = new ArrayList<BoardSpot>();
		darkGraveyardSpots = new ArrayList<BoardSpot>();

		this.currentPlayer = currentPlayer;
		sourceLocation = null;

		playerOnePanel = new JPanel();
		playerTwoPanel = new JPanel();

		if(this.currentPlayer)
			setTitle("JChess - Light's Turn");
		else
			setTitle("JChess - Dark's Turn");
		setDimensions();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.lightPlayer = lightPlayer;
		this.darkPlayer = darkPlayer;

		bringingBackTheDead = false;
		deadPiece = null;

		inCheckMate = false;
	}

	/*
	 * setDimensions()
	 *
	 * The purpose of this method is to set the dimensions of the chess
	 * board to the screen size.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Ouput:
	 * 	N/A
	 */

	private void setDimensions()
	{
		// Get the screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Set the board size
		setSize(screenSize.width,screenSize.height);
	}

	public void run()
	{
		board = new Board();

		board.enablePiecesByColor(currentPlayer);

		display();
	}

    /**
     * Runs the saved game based on values returned from the gamedata.txt file
     * @param board Object received from the file
     * @param isAlreadyLoaded True if the game is already loaded, prevents duplication
     */
	private void runSavedGame(Board board, boolean isAlreadyLoaded)
	{
		this.board = board;
		this.board.enablePiecesByColor(this.currentPlayer);
		display();
		this.inCheck = isInCheck(this.currentPlayer);
		if (this.inCheck) {
			String player = (this.currentPlayer ? "Light" : "Dark");
			setTitle(player + " is in check");
		}
		this.isAlreadyLoaded = !isAlreadyLoaded;
	}

	/*
	 * display()
	 *
	 * The purpose of this method is to display the BoardGUI
	 * based on the current status of variables.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	N/A
	 */

	@SuppressWarnings("WeakerAccess")
	public void display()
	{
		// Set all of the board spots to be displayed based on our board
		setBoardSpots();

		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.removeAll();

		setGridPanel();

		setGraveyardSpots(lightPlayer);
		setGraveyardSpots(darkPlayer);

		playerOnePanel = new JPanel();
		playerTwoPanel = new JPanel();

		setPlayerGraveYardPanel(playerOnePanel,lightPlayer, lightGraveyardSpots);
		setPlayerGraveYardPanel(playerTwoPanel,darkPlayer, darkGraveyardSpots);

		contentPane.add(boardPanel,BorderLayout.CENTER);
		contentPane.add(playerOnePanel, BorderLayout.WEST);
		contentPane.add(playerTwoPanel, BorderLayout.EAST);

		contentPane.add(addMenuBar(), BorderLayout.NORTH);

		setVisible(true);

	}

	/**
	 * Creates an option menu
	 * @return JMenuBar
	 */
	private JMenuBar addMenuBar()
	{
		JMenuBar menuBar;
		JMenuItem menuItem, menuItem2, menuItem3;
		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		menu.setFont(new Font("Ariel",Font.BOLD,12));
		menuItem = new JMenuItem("Save Game");
		menuItem2 = new JMenuItem("Load Game");
		menuItem3 = new JMenuItem("Rematch");

		menuItem.addActionListener((event) -> saveGame());
		menuItem2.addActionListener((event) -> loadGame());
		menuItem3.addActionListener((event) -> rematch());

		menuItem.setFont(new Font("Ariel",Font.BOLD,12));
		menuItem2.setFont(new Font("Ariel",Font.BOLD,12));
		menuItem3.setFont(new Font("Ariel",Font.BOLD,12));

		menu.add(menuItem);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menuBar.add(menu);

		menuBar.setSize(new Dimension(10,10));
		return menuBar;
	}

	/**
	 * Allows for rematch
	 * //@param event Button Clicked
	 */
	private void rematch()
	{
		this.dispose();
		BoardGUI boardGUI = new BoardGUI();
		boardGUI.run();
	}

	/**
	 * Loads the game from the saved data
	 */
	private void loadGame()
	{
		if(!isAlreadyLoaded) {
			dispose();
			gameState = new GameState(true);
			board = gameState.getBoard();
			currentPlayer = gameState.getPlayerData(lightPlayer, darkPlayer);
			gameState.close();

			BoardGUI boardGUI = new BoardGUI(lightPlayer,darkPlayer,currentPlayer);
			boardGUI.runSavedGame(board,isAlreadyLoaded);

		}
	}

	/**
	 * Saves the Game data
	 */
	private void saveGame()
	{
		gameState = new GameState(false);
		gameState.writeToFile(board,lightPlayer,darkPlayer, currentPlayer);
		gameState.close();
	}

	// Creates graveyard, shows points and player number
	private void setPlayerGraveYardPanel(JPanel panel, Player player, ArrayList<BoardSpot> playerGraveyardSpots)
	{
		panel.setLayout(new BorderLayout());
		panel.setOpaque(true);

		JPanel topPanel = new JPanel();
		GridLayout grave = new GridLayout(8,1);
		topPanel.setLayout(grave);
		topPanel.setOpaque(true);
		topPanel.setPreferredSize(new Dimension(150,Toolkit.getDefaultToolkit().getScreenSize().height));

		String info = "<html>" + player.getPlayerName() + "<br/># Points: " + player.getNumPoints() + "</html>";
		JLabel nameLabel = new JLabel(info, SwingConstants.CENTER); // player name
		nameLabel.setFont(new Font("Sans Serif", Font.BOLD,17));

		nameLabel.setPreferredSize(new Dimension(150,25));

		topPanel.add(nameLabel);

		topPanel.setBackground(new Color(215, 153, 20));

		addToGraveyard(topPanel, playerGraveyardSpots);

		panel.add(topPanel,BorderLayout.NORTH); // IT WILL ALWAYS FILL FROM THE TOP TO BOTTOM

	}

	// Adds pieces to the graveyard, if any.
	private void addToGraveyard(JPanel playerPanel, ArrayList<BoardSpot> playerGraveyardSpots)
	{
		int size;

		size = playerGraveyardSpots.size();

		for(int i =0;i < size; i++)
		{
			playerPanel.add(playerGraveyardSpots.get(i));
		}
	}

	/*
	 * switchTurn()
	 *
	 * The purpose of this method is to handle the change of players turn
	 * (based on the currentPlayer variable) by enabling only the current
	 * players pieces, and changing the GUI frame's title.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	N/A
	 *
	 */

	public void switchTurn()
	{
		// Change the currentPlayer variable to reflect the currentPlayer
		currentPlayer = !currentPlayer;

		board.disableAllSquares();
		board.unhighlightAllSquares();

		board.enablePiecesByColor(currentPlayer);

		// Change the frame's title
		String player;
		if(currentPlayer == DARK){
			player = "Dark";
		}
		else{
			player = "Light";
		}

		if(inCheckMate)
		{
			if(currentPlayer != DARK){
				player = "Dark";
			}
			else{
				player = "Light";
			}
			String message = "CHECKMATE: GAME OVER! " + player + " wins!";
			setTitle(message);
			board.disableAllSquares();
			showGameDialog(player);
		}
		else {
			if (inCheck) {
				setTitle(player + " is in check");
				// JOptionPane.showMessageDialog(contentPane,player+ " is in check");
			} else if (currentPlayer == LIGHT) {
				setTitle("JChess - Light's Turn");
			} else {
				setTitle("JChess - Dark's Turn");
			}
		}

	}

	private void showGameDialog(String player)
	{
		isGameOver = true;
		int result = JOptionPane.showConfirmDialog(contentPane,player + " wins!   REMATCH ?"
				,"CHECK MATE", JOptionPane.YES_NO_OPTION);
		if(result == 0)
			rematch();
		else
			System.exit(0);
	}


	private void setGridPanel()
	{
		boardPanel = new JPanel();
		gridLayout = new GridLayout(8,8);

		boardPanel.setLayout(gridLayout);
		int row, col;
		for(row = 0; row < 8; row++)
		{
			for(col = 0; col < 8; col++)
			{
				boardPanel.add(boardSpots[row][col]);
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		Piece p;
		BoardSpot deadPieceSpot;
		int i, j;
		Pair clicked;
		int size;

		// Is someone trying to bring a piece back from the dead??
		size = lightGraveyardSpots.size();

		for(i = 0; i < size; i++)
		{
			deadPieceSpot = lightGraveyardSpots.get(i);

			if(deadPieceSpot == e.getSource())
			{
				locationOfDead = i;
				deadPiece = lightPlayer.getGraveyard().getPiece(i);
				handleGraveyardPieceSourceSelection();
			}

		}

		size = darkGraveyardSpots.size();

		for(i = 0; i < size; i++)
		{
			deadPieceSpot = darkGraveyardSpots.get(i);

			if(deadPieceSpot == e.getSource())
			{
				locationOfDead = i;
				deadPiece = darkPlayer.getGraveyard().getPiece(i);
				handleGraveyardPieceSourceSelection();
			}
		}

		// Were one of our chess buttons clicked??
		for(i = 0; i < 8; i++)
		{
			for(j = 0; j < 8; j++)
			{
				if(boardSpots[i][j] == e.getSource()) {
					p = board.getSquare(i, j).getPiece();
					clicked = new Pair(i,j);

					if(bringingBackTheDead)
					{
						handleGraveyardPieceDestSelection(clicked);
					}

					else if(currentlyMoving &&  p!= null && p.getColor() == currentPlayer){
						handlePieceSourceSelection(clicked);
						return;

					}
					else if (p == null || currentlyMoving) {
						handlePieceDestinationSelection(clicked);
						return;
					}
					else
					{
						handlePieceSourceSelection(clicked);
						return;
					}
					j = 8;
					i = 8;
				}
			}
		}

	}

	public void handleGraveyardPieceDestSelection(Pair dest)
	{
		int cost;
		board.getSquare(dest).setPiece(deadPiece);

		cost = Graveyard.getCost(deadPiece);

		if(currentPlayer == LIGHT)
		{
			lightPlayer.getGraveyard().removePiece(locationOfDead);
			lightPlayer.removePoints(cost);
		}
		else
		{
			darkPlayer.getGraveyard().removePiece(locationOfDead);
			darkPlayer.removePoints(cost);
		}

		bringingBackTheDead = false;

		inCheck = isInCheck(!currentPlayer);
		inCheckMate = isInCheckMate(!currentPlayer);
		switchTurn();
		if(!isGameOver) {
			display();
		}
	}




	public void handleGraveyardPieceSourceSelection()
	{
		if(currentPlayer == LIGHT)
		{
			board.highlightEnableBottomRows();
		}
		else
		{
			board.highlightEnableTopRows();
		}

		bringingBackTheDead = true;

		display();
	}

	public void handlePieceSourceSelection(Pair source)
	{
		Moves moves;

		moves = board.getValidMoves(source);

		board.unhighlightAllSquares();
		board.disableAllSquares();
		board.enablePiecesByColor(currentPlayer);

		board.highlightSquares(moves);
		board.enableSquares(moves);

		display();

		sourceLocation = source;

		board.unhighlightAllSquares();
		currentlyMoving = true;

	}

	public void handlePieceDestinationSelection(Pair dest)
	{//self documenting
		String name = "";
		String message;
		Square destSquare = board.getSquare(dest);
		Piece destPiece = destSquare.getPiece();
		Piece pieceForCheck;
		boolean dontSwitchTurn = false;



		Pair sourcePair = sourceLocation;
		Piece sourcePiece = board.getSquare(sourcePair).getPiece();

		if(sourcePiece instanceof Pawn)
		{
			((Pawn) sourcePiece).setFirstMoveToFalse();
		}

		if(destPiece != null)
		{
			destPiece = destSquare.popPiece();
			name = destPiece.getName();
			handleCapturedPiece(destPiece);
		}

		pieceForCheck = destPiece;
		board.getSquare(dest).setPiece(sourcePiece);
		board.getSquare(sourcePair).setPiece(null);

		if(isInCheck(currentPlayer)){
			String player;
			if(currentPlayer == DARK){
				player = "Dark";
			}
			else{
				player = "Light";
			}
			board.getSquare(dest).setPiece(pieceForCheck);
			board.getSquare(sourcePair).setPiece(sourcePiece);
			if(sourcePiece instanceof Pawn){
			    ((Pawn) sourcePiece).setFirstMoveToTrue();
            }
			dontSwitchTurn = true;
			message = player + " moved themselves into check, choose a different move";
			setTitle(message);
			JOptionPane.showMessageDialog(contentPane,message);
			board.disableAllSquares();
			board.enablePiecesByColor(currentPlayer);
		}

		if(destSquare1 == null) destSquare1 = board.getSquare(dest.getRow(), dest.getCol());
		else destSquare2 = board.getSquare(dest.getRow(), dest.getCol());


		board.unhighlightAllSquares();

		inCheck = isInCheck(!currentPlayer);
		inCheckMate = isInCheckMate(!currentPlayer);
		if(!dontSwitchTurn) {
			board.disableAllSquares();
			switchTurn();
		}
		if(!isGameOver) {
			display();
		}
		currentlyMoving = false;
	}

	private void handleCapturedPiece(Piece captured)
	{
		if(currentPlayer == LIGHT && captured != null)
		{
			if(captured instanceof Pawn)
				lightPlayer.addPoints(PAWN_VALUE);
			else
				darkPlayer.getGraveyard().addToGraveyard(captured);
		}
		else if(currentPlayer == DARK && captured != null)
		{
			if(captured instanceof Pawn)
				darkPlayer.addPoints(PAWN_VALUE);
			else
				lightPlayer.getGraveyard().addToGraveyard(captured);
		}
	}

	/*
	 * isInCheck()
	 *
	 * The purpose of this method is to check whether or not
	 * the king of the given color is in check.
	 *
	 * Input:
	 * 	boolean kingColor	// the color of the king
	 *
	 * Output:
	 * 	boolean ans	// whether or not that king is in check
	 *
	 */

	public boolean isInCheck(boolean kingColor)//the color parameter is the color king that we are checking for check
	{
		Piece p;
		boolean ans = false;
		Pair kingLoc = board.findKing(kingColor);

		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				p = board.getSquare(i,j).getPiece();
				if(p == null)
				{
					continue;
				}
				else if(p.getColor() != kingColor && p != null)
				{//if the piece on this spot has a valid move to where the king is located, we go into check
					Moves m = board.getValidMoves(new Pair(i, j));
					if(m != null) 
					{
						if (m.findPair(kingLoc.getRow(), kingLoc.getCol()) != -1) 
						{
							ans = true;
						}
					}
				}
			}
		}
		return ans;
	}

	/**
	 *
	 * @param kingColor color of the player we are evaluating checkmate for
	 * @return if the player is in checkmate or not
	 *
	 *
	 */
	public boolean isInCheckMate(boolean kingColor)
	{
		boolean ans = false;//if we are in checkmate or not
		Pair pair;
		Piece alt;
		Piece other;
		Moves altMoves;

		if(isInCheck(kingColor))//in order to evaluate checkmate, we first need to be in check
		{
			ans = true;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {//loop thru the entire board
					pair = new Pair(i, j);
					alt = board.getSquare(pair).getPiece();
					if(alt != null && alt.getColor() == kingColor){
						altMoves = board.getValidMoves(pair);
						//gets a piece and its moveset of the same color we are evaluating
						for (int k = 0; k < altMoves.getSize(); k++) {
							other = board.getSquare(altMoves.getPair(k)).getPiece();
							board.getSquare(altMoves.getPair(k)).setPiece(alt);
							//if we place that piece and it takes us out of check, we are not in checkmate
							if(!isInCheck(kingColor)){
								ans = false;
							}
							//we dont want to actually place these pieces so we need to reset the potential moves
							board.getSquare(altMoves.getPair(k)).setPiece(other);
						}
					}
				}
			}
		}

		return ans;
	}

	private void disableGraveyardSpots(Player player)
	{
		if(player == lightPlayer)
		{
			for(int i = 0; i < lightGraveyardSpots.size(); i++)
			{
				lightGraveyardSpots.get(i).setEnabled(false);
			}
		}
		else
		{
			for(int i = 0; i < darkGraveyardSpots.size(); i++)
				darkGraveyardSpots.get(i).setEnabled(false);

		}
	}

	private void setGraveyardSpots(Player player)
	{
		Piece currentPiece;
		Graveyard graveyard;
		String pieceName;
		int numPieces;
		int numPoints;

		if(player == lightPlayer) // Setting Player 1's Graveyard spots
		{
			lightGraveyardSpots = new ArrayList<BoardSpot>();
			graveyard = player.getGraveyard();
			numPieces = graveyard.getNumPieces();

			for(int i = 0; i < numPieces; i++)
			{
				numPoints = lightPlayer.getNumPoints();
				currentPiece = graveyard.getPiece(i);
				pieceName = currentPiece.getName();

				switch(pieceName)
				{
					case "Rook":
						lightGraveyardSpots.add(new BoardSpot(LIGHT_ROOK));
						break;
					case "Knight":
						lightGraveyardSpots.add(new BoardSpot(LIGHT_KNIGHT));
						break;
					case "Bishop":
						lightGraveyardSpots.add(new BoardSpot(LIGHT_BISHOP));
						break;
					case "Queen":
						lightGraveyardSpots.add(new BoardSpot(LIGHT_QUEEN));
						break;
				}

				if((numPoints >= Graveyard.getCost(currentPiece)) && (currentPlayer == LIGHT))
				{
					lightGraveyardSpots.get(i).setEnabled(true);
				}
				else
				{
					lightGraveyardSpots.get(i).setEnabled(false);
				}

				lightGraveyardSpots.get(i).addActionListener(this);
			}

			if(inCheck)
			{
				disableGraveyardSpots(player);
			}


		}
		else
		{
			darkGraveyardSpots = new ArrayList<BoardSpot>();
			graveyard = player.getGraveyard();
			numPieces = graveyard.getNumPieces();

			for(int i = 0; i < numPieces; i++)
			{
				numPoints = darkPlayer.getNumPoints();
				currentPiece = graveyard.getPiece(i);
				pieceName = currentPiece.getName();

				switch(pieceName)
				{
					case "Rook":
						darkGraveyardSpots.add(new BoardSpot(DARK_ROOK));
						break;
					case "Knight":
						darkGraveyardSpots.add(new BoardSpot(DARK_KNIGHT));
						break;
					case "Bishop":
						darkGraveyardSpots.add(new BoardSpot(DARK_BISHOP));
						break;
					case "Queen":
						darkGraveyardSpots.add(new BoardSpot(DARK_QUEEN));
						break;
				}

				if((numPoints >= Graveyard.getCost(currentPiece)) && (currentPlayer == DARK))
				{
					darkGraveyardSpots.get(i).setEnabled(true);
				}
				else
				{
					darkGraveyardSpots.get(i).setEnabled(false);
				}

				darkGraveyardSpots.get(i).addActionListener(this);
			}

			if(inCheck)
				disableGraveyardSpots(player);
		}
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
