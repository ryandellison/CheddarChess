package player;

import board.Board;
import pieces.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class is to either save the game and load a game
 */

public class GameState
{
    // Hardcoded file path
    private static final String filePath = "SaveData.txt";
    private PrintWriter printWriter;
    private FileWriter fileWriter;
    private Scanner scanner;
    //Will board data
    private ArrayList<String[]> data;
    private boolean gameDataExists = false;

    /**
     * Creates a new gameState that either reads or writes to file
     *
     */
    public GameState(boolean readonly)
    {
        if(!readonly) // Get the file ready to be written too
            initialize();
        else
            initializeReadOnly(); // Get ready to read
    }

    /**
     * Initializes the write process
     */
    private void initialize()
    {
        // opens file
        try {
            fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter = new PrintWriter(fileWriter);
    }

    /**
     * Initializes the read process
     */
    private void initializeReadOnly()
    {
        //opens file
        try {
            scanner = new Scanner(new File(filePath));
        } catch (IOException e) {
            gameDataExists = false;
        }
    }

    /**
     * Gets the line by line data for the board
     *
     */
    private void readFile()
    {
        data = new ArrayList<>(64);
        int i = 1;
        while (scanner.hasNextLine() && i < 65) {
            // CSV line creates an arraylist where each index represents a parameter for the board/square class
            data.add(scanner.nextLine().split(","));
            i++;
        }
        scanner.nextLine();// move to the next line
    }

    /**
     * Gets the Board representation from the file
     * @return Board Object
     */
    public Board getBoard()
    {
        readFile();
        gameDataExists = true;
        return new Board(data); // New Board that will be used to load the game
    }

    public boolean isSaveDataExists()
    {
        return gameDataExists;
    }
    /**
     * Gets Player data from the file
     * @param player1 Data for Player1/LIGHT
     * @param player2 Data for Player2/DARK
     * @return True is Light's turn
     */
    public boolean getPlayerData(Player player1, Player player2)
    {
        getPlayer(player1);
        scanner.nextLine(); // skip next line
        getPlayer(player2);
        return Boolean.parseBoolean(scanner.nextLine()); // returns true is Light's turn, dark if otherwise
    }

    /**
     * Gets the data for the individual player
     * @param player = Particular player
     */
    private void getPlayer(Player player)
    {
        String[] pieceData;
        Graveyard graveyard = player.getGraveyard(); // Gets new graveyard
        player.addPoints(Integer.parseInt(scanner.nextLine())); // adds points to new player
        int numPieces = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < numPieces; i++){
            pieceData = scanner.nextLine().split(","); // represents the piece name and if it light or dark
            graveyard.addToGraveyard(convertToPiece(pieceData[0],pieceData[1])); // adds the piece to graveyard
        }
    }

    /**
     * Converts the piece data to a Piece object
     * @param name Piece name
     * @param colorValue Piece colo
     * @return Piece object
     */
    private Piece convertToPiece(String name, String colorValue)
    {
        Piece piece;
        boolean color = Boolean.parseBoolean(colorValue);
        switch (name){ // name of the piece
            case "Pawn":
                piece = new Pawn(color,name);
                break;
            case "Rook":
                piece = new Rook(color,name);
                break;
            case "Knight":
                piece = new Knight(color,name);
                break;
            case "Bishop":
                piece = new Bishop(color,name);
                break;
            case "King":
                piece = new King(color,name);
                break;
            case "Queen":
                piece = new Queen(color,name);
                break;
            default:
                piece = null;
                break;
        }
        return piece; // piece that was newly created
    }

    /**
     * Writes to file, used for saving game
     * @param board Board object
     * @param player1 Player object
     * @param player2 Player object
     * @param current Whose turn it is
     */
    public void writeToFile(Board board, Player player1, Player player2, boolean current)
    {
        String text;
        for(int i = 0; i < 8;i++){
            for(int j = 0; j < 8; j++){
                text = board.getSquare(i,j).toString(); // Each squares' data
                printWriter.append(text).append("\n"); // stored in comma seperated value
            }
        }

        printWriter.append("Player 1\n"); // player 1 section
        writeGraveyardToFile(player1); // gets all the player 1 graveyard pieces and writes to file
        printWriter.append("Player 2\n"); // player 2 section
        writeGraveyardToFile(player2); // gets all player 2 graveyard pieces and writes to file
        printWriter.append(Boolean.toString(current)).append("\n"); // stores whose turn it is
    }

    /**
     * Writes the graveyard portion for each player
     * @param player Player object
     */
    private void writeGraveyardToFile(Player player)
    {
        printWriter.append(Integer.toString(player.getNumPoints())).append("\n"); // store numPoints of player
        Graveyard graveyard = player.getGraveyard();
        int deadPieces = graveyard.getNumPieces(); // num of pieces in the graveyard
        printWriter.append(Integer.toString(deadPieces)).append("\n"); // store how many pieces in graveyard
        if(deadPieces > 0){
            for(int i = 0 ; i < deadPieces; i++){
                printWriter.append(graveyard.getPiece(i).getName()).append(","); // name of dead piece
                printWriter.append(Boolean.toString(graveyard.getPiece(i).getColor())); // color of dead piece
                printWriter.append("\n");
            }
        }
    }

    /**
     * Closes the files
     */
    public void close()
    {
        if(printWriter!=null)printWriter.close();
        if(scanner!= null)scanner.close();
    }
}
