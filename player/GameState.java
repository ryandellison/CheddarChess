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
    private static final String filePath = "GameData.txt";
    private PrintWriter printWriter;
    private FileWriter fileWriter;
    private Scanner scanner;
    private ArrayList<String[]> data;

    /**
     * Creates a new gameState that either reads or writes to file
     *
     */
    public GameState(boolean readonly)
    {
        if(!readonly)
            initialize();
        else
            initializeReadOnly();
    }

    /**
     * Initializes the write process
     */
    private void initialize()
    {
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
        try {
            scanner = new Scanner(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
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
            data.add(scanner.nextLine().split(","));
            i++;
        }
        scanner.nextLine();
    }

    /**
     * Gets the Board representation from the file
     * @return Board Object
     */
    public Board getBoard()
    {
        readFile();
        return new Board(data);
    }

    /**
     * Gets Player data from the file
     * @param player1 Data for Player1/LIGHT
     * @param player2 Data for Player2/DARK
     */
    public void getPlayerData(Player player1, Player player2)
    {
        getPlayer(player1);
        getPlayer(player2);

    }

    /**
     * Gets the data for the individual player
     * @param player = Particular player
     */
    private void getPlayer(Player player)
    {
        String[] pieceData;
        Graveyard graveyard = player.getGraveyard();
        player.addPoints(Integer.parseInt(scanner.nextLine()));
        int numPieces = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < numPieces; i++){
            pieceData = scanner.nextLine().split(",");
            graveyard.addToGraveyard(convertToPiece(pieceData[0],pieceData[1]));
        }
        if(scanner.hasNextLine()){
            scanner.nextLine();
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
        switch (name){
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
        return piece;
    }

    /**
     * Writes to file, used for saving game
     * @param board Board object
     * @param player1 Player object
     * @param player2 Player object
     */
    public void writeToFile(Board board, Player player1, Player player2)
    {
        String text;
        for(int i = 0; i < 8;i++){
            for(int j = 0; j < 8; j++){
                text = board.getSquare(i,j).toString();
                printWriter.append(text).append("\n");
            }
        }

        printWriter.append("Player 1\n");
        writeGraveyardToFile(player1);
        printWriter.append("Player 2\n");
        writeGraveyardToFile(player2);
    }

    /**
     * Writes the graveyard portion for each player
     * @param player Player object
     */
    private void writeGraveyardToFile(Player player)
    {
        printWriter.append(Integer.toString(player.getNumPoints())).append("\n");
        Graveyard graveyard = player.getGraveyard();
        int deadPieces = graveyard.getNumPieces();
        printWriter.append(Integer.toString(deadPieces)).append("\n");
        if(deadPieces > 0){
            for(int i = 0 ; i < deadPieces; i++){
                printWriter.append(graveyard.getPiece(i).getName()).append(",");
                printWriter.append(Boolean.toString(graveyard.getPiece(i).getColor()));
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
