package player;

//***** WORK IN PROGRESS

import board.Board;
import graphics.BoardSpot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameState
{
    private File logFile;
    private static final String filePath = "GameData.txt";
    private PrintWriter printWriter;
    private FileWriter fileWriter;
    private Scanner scanner;

    public GameState()
    {
        initialize();

    }
    private void initialize()
    {
        try {
            fileWriter = new FileWriter(filePath,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter = new PrintWriter(fileWriter);
    }

    public void openFile()
    {
        try {
            scanner = new Scanner(new File("/Users/pankaj/Downloads/myfile.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BoardSpot[][] getBoard(Board board)
    {
        BoardSpot[][] boardSpots = new BoardSpot[8][8];
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        return boardSpots;
    }

    public void writeToFile(String message)
    {
        printWriter.println(message);
    }

    public void close()
    {
        if(printWriter!=null)printWriter.close();
        if(scanner!= null)scanner.close();
    }
}
