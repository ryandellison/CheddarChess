/*
 * BoardGUI
 *
 * The purpose of this class is to represent the chess board GUI.
 *
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

	// CONSTANTS AND VARIABLES FOR GUI
	private static final String EMPTY_PIECE = "";
	private BoardSpot[][] boardSpots;
	private ArrayList<BoardSpot> player1GraveyardSpots;
	private ArrayList<BoardSpot> player2GraveyardSpots;
	private GridLayout gridLayout;
	private JPanel boardPanel;
	private JPanel playerOnePanel;
	private JPanel playerTwoPanel;
	private Container contentPane;
	private JMenu menu;

	private Board board;
	private Player player1;
	private Player player2;
	private boolean currentPlayer;
	private Pair sourceLocation;
	private Square destSquare1, destSquare2;
	private boolean currentlyMoving = false;
	private MovesHistory history;
	private int turn = 0;
	private boolean isGraveyardPiece = false;
	
	private boolean bringingBackTheDead;
	private Piece deadPiece;
	private int locationOfDead;

	public BoardGUI()
	{
		boardSpots = new BoardSpot[8][8];

		player1GraveyardSpots = new ArrayList<BoardSpot>();
		player2GraveyardSpots = new ArrayList<BoardSpot>();

		currentPlayer = LIGHT;
		sourceLocation = null;

		playerOnePanel = new JPanel();
		playerTwoPanel = new JPanel();

		setTitle("JChess - Light's Turn");
		setDimensions();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		player1 = new Player(LIGHT);
		player2 = new Player(DARK);

		bringingBackTheDead = false;
		deadPiece = null;
	}

	private void setDimensions()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
	}

	public void run()
	{
		board = new Board();

		board.enablePiecesByColor(currentPlayer);

		display();
		history = new MovesHistory(board);
	}

	/*
	 * display()
	 *
	 * The purpose of this method is to display the BoardGUI
	 * based on the current board by running setBoardSpots()
	 * and refreshing the content pane.
	 *
	 * Input:
	 * 	N/A
	 *
	 * Output:
	 * 	N/A
	 *
	 */

	@SuppressWarnings("WeakerAccess")
	public void display()
	{
		setBoardSpots();

		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.removeAll();

		setGridPanel();

		setGraveyardSpots(player1);
		setGraveyardSpots(player2);

		playerOnePanel = new JPanel();
		playerOnePanel = new JPanel();

		setPlayerGraveYardPanel(playerOnePanel,player1, player1GraveyardSpots);
		setPlayerGraveYardPanel(playerTwoPanel,player2, player2GraveyardSpots);

		contentPane.add(boardPanel,BorderLayout.CENTER);
		contentPane.add(playerOnePanel, BorderLayout.WEST);
		contentPane.add(playerTwoPanel, BorderLayout.EAST);

		contentPane.add(addMenuBar(), BorderLayout.NORTH);

		setVisible(true);
	}

	private JMenuBar addMenuBar()
	{
		JMenuBar menuBar;
		JMenuItem menuItem, menuItem2;
		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		menu.setFont(new Font("Ariel",Font.BOLD,12));
		menuItem = new JMenuItem("Save Game");
		menuItem2 = new JMenuItem("Load Game");

		menuItem.addActionListener((event) -> saveGame());
		menuItem.addActionListener((event) -> loadGame());

		menuItem.setFont(new Font("Ariel",Font.BOLD,12));
		menuItem2.setFont(new Font("Ariel",Font.BOLD,12));

		menu.add(menuItem);
		menu.add(menuItem2);
		menuBar.add(menu);
		menuBar.setSize(new Dimension(10,10));
		return menuBar;
	}

	private void loadGame()
	{

	}

	private void saveGame()
	{

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

		String info = player.getPlayerNum() + "           " + player.getNumPoints();
		JLabel nameLabel = new JLabel(info, SwingConstants.CENTER); // player name
		nameLabel.setFont(new Font("Ariel", Font.BOLD,15));

		nameLabel.setPreferredSize(new Dimension(150,25));

		topPanel.add(nameLabel);

		topPanel.setBackground(new Color(34, 107, 214));

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
		// write to file
		
		/*
		if(currentPlayer == LIGHT) 
		{
			setTitle("JChess - Light's Turn");
			if(turn > 0) 
			{
				history.write(destSquare1, destSquare2, turn);
				destSquare1 = null; destSquare2 = null;
			}
		} 
		else 
		{
			setTitle("JChess - Dark's Turn");
			turn++;
		}
		*/
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
		size = player1GraveyardSpots.size();

		for(i = 0; i < size; i++)
		{
			deadPieceSpot = player1GraveyardSpots.get(i);

			if(deadPieceSpot == e.getSource())
			{
				locationOfDead = i;
				deadPiece = player1.getGraveyard().getPiece(i);
				handleGraveyardPieceSourceSelection();
			}
			
		}

		size = player2GraveyardSpots.size();

		for(i = 0; i < size; i++)
		{
			deadPieceSpot = player2GraveyardSpots.get(i);

			if(deadPieceSpot == e.getSource())
			{
				locationOfDead = i;
				deadPiece = player2.getGraveyard().getPiece(i);
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
			player1.getGraveyard().removePiece(locationOfDead);
			player1.removePoints(cost);
		}
		else
		{
			player2.getGraveyard().removePiece(locationOfDead);
			player2.removePoints(cost);
		}

		bringingBackTheDead = false;

		switchTurn();

		display();
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

		board.highlightSquares(moves);
		board.enableSquares(moves);

		display();

		sourceLocation = source;

		board.unhighlightAllSquares();
		currentlyMoving = true;

	}

	public void handlePieceDestinationSelection(Pair dest)
	{
		String name = "";
		Square destSquare = board.getSquare(dest);
		Piece destPiece = destSquare.getPiece();
		
		if(destPiece != null)
		{
			destPiece = destSquare.popPiece();
			name = destPiece.getName();
			handleCapturedPiece(destPiece);
		}

		Pair sourcePair = sourceLocation;
		Piece sourcePiece = board.getSquare(sourcePair).getPiece();

		if(sourcePiece instanceof Pawn)
		{
			((Pawn) sourcePiece).setFirstMoveToFalse();
		}

		board.getSquare(dest).setPiece(sourcePiece);
		board.getSquare(sourcePair).setPiece(null);

		if(destSquare1 == null) destSquare1 = board.getSquare(dest.getRow(), dest.getCol());
		else destSquare2 = board.getSquare(dest.getRow(), dest.getCol());

		board.disableAllSquares();
		board.unhighlightAllSquares();

		switchTurn();

		display();
		currentlyMoving = false;
	}

	private void handleCapturedPiece(Piece captured)
	{
		if(currentPlayer == LIGHT && captured != null)
		{
			if(captured instanceof Pawn)
				player1.addPoints(PAWN_VALUE);
			else
				player2.getGraveyard().addToGraveyard(captured);
		}
		else if(currentPlayer == DARK && captured != null)
		{
			if(captured instanceof Pawn)
				player2.addPoints(PAWN_VALUE);
			else
				player1.getGraveyard().addToGraveyard(captured);
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
				else if(p.getColor() != kingColor)
				{//if the piece on this spot has a valid move to where the king is located, we go into check
					Moves m = board.getValidMoves(new Pair(i, j));
					if(m.findPair(kingLoc.getRow(), kingLoc.getCol()) != -1)
					{
						ans = true;
					}
				}
			}
		}
		return ans;
	}

	public boolean isInCheckMate(boolean kingColor)
	{
		Piece p;
		Square s;
		boolean ans = false;
		boolean tracker = true;
		Pair kingLoc = board.findKing(kingColor);
		Pair attackDest;
		Square kingSquare = board.getSquare(kingLoc);
		Moves kingMoves = board.getValidMoves(kingLoc);
		Moves attackMoves;
		//this will only go thru if the king is already in check
		if(isInCheck(kingColor))
		{
			if(kingMoves.getSize() == 0)//if the king cannot move and there is a valid move to reach it, checkmate
			{
				ans = true;
			}
			else
			{
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						s = board.getSquare(i,j);
						p = s.getPiece();
						attackMoves = board.getValidMoves(new Pair(i, j));
						if(p == null)
						{
							continue;
						}//p is the piece again, loop thru and get all of the pieces that are the opposite color
						//of the king
						else if(p.getColor() != kingColor)
						{
							for (int x = 0; x < attackMoves.getSize(); x++)
							{
								attackDest = attackMoves.getPair(x);
								if(kingMoves.findPair(attackDest.getRow(),attackDest.getCol()) != -1)//for the valid
								//king moves, if the attacker can move to an available move, remove that move from
								//the possible king moves
								{
									// kingMoves.removeMove(new Pair(attackDest.getRow(),attackDest.getCol()));
								}
							}
						}
					}
				}
				if(kingMoves.getSize() == 0)//if all possible moves have been removed, we are in checkmate
				{
					ans = true;
				}
			}
		}

		return ans;
	}

	public void setGraveyardSpots(Player player)
	{
		Piece currentPiece;
		Graveyard graveyard;
		String pieceName;
		int numPieces;
		int numPoints;

		if(player == player1) // Setting Player 1's Graveyard spots
		{
			player1GraveyardSpots = new ArrayList<BoardSpot>();
			graveyard = player.getGraveyard();
			numPieces = graveyard.getNumPieces();

			for(int i = 0; i < numPieces; i++)
			{
				numPoints = player1.getNumPoints();
				currentPiece = graveyard.getPiece(i);
				pieceName = currentPiece.getName();
				
				switch(pieceName)
				{
					case "Rook":
						player1GraveyardSpots.add(new BoardSpot(LIGHT_ROOK));
						break;
					case "Knight":
						player1GraveyardSpots.add(new BoardSpot(LIGHT_KNIGHT));
						break;
					case "Bishop":
						player1GraveyardSpots.add(new BoardSpot(LIGHT_BISHOP));
						break;
					case "Queen":
						player1GraveyardSpots.add(new BoardSpot(LIGHT_QUEEN));
						break;
				}
					
				if((numPoints >= Graveyard.getCost(currentPiece)) && (currentPlayer == LIGHT))
				{
					player1GraveyardSpots.get(i).setEnabled(true);
				}
				else
				{
					player1GraveyardSpots.get(i).setEnabled(false);
				}

				player1GraveyardSpots.get(i).addActionListener(this);
			}
			
			
		}
		else
		{
			player2GraveyardSpots = new ArrayList<BoardSpot>();
			graveyard = player.getGraveyard();
			numPieces = graveyard.getNumPieces();

			for(int i = 0; i < numPieces; i++)
			{
				numPoints = player2.getNumPoints();
				currentPiece = graveyard.getPiece(i);
				pieceName = currentPiece.getName();
				
				switch(pieceName)
				{
					case "Rook":
						player2GraveyardSpots.add(new BoardSpot(DARK_ROOK));
						break;
					case "Knight":
						player2GraveyardSpots.add(new BoardSpot(DARK_KNIGHT));
						break;
					case "Bishop":
						player2GraveyardSpots.add(new BoardSpot(DARK_BISHOP));
						break;
					case "Queen":
						player2GraveyardSpots.add(new BoardSpot(DARK_QUEEN));
						break;
				}

				if((numPoints >= Graveyard.getCost(currentPiece)) && (currentPlayer == DARK))
				{
					player2GraveyardSpots.get(i).setEnabled(true);
				}
				else
				{
					player2GraveyardSpots.get(i).setEnabled(false);
				}

				player2GraveyardSpots.get(i).addActionListener(this);
			}
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
