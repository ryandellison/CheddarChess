package player;

//***** WORK IN PROGRESS

import board.Board;
import graphics.BoardSpot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameState
{
    private File logFile;
    private static final String filePath = "GameData.txt";
    private PrintWriter printWriter;
    private FileWriter fileWriter;
    private Scanner scanner;
    private boolean readonly;
    private ArrayList<String[]> data;

    public GameState(boolean readonly)
    {
        this.readonly = readonly;
        if(!this.readonly)
            initialize();

    }
    private void initialize()
    {
        try {
            fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter = new PrintWriter(fileWriter);
    }

    public ArrayList<String[]> readFile()
    {
        data = new ArrayList<>(64);
        int i = 0;
        try {
            scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine().split(","));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public Board getBoard()
    {
        readFile();
        return new Board(data);
    }

    public void writeToFile(Board board)
    {
        String text ="";
        for(int i = 0; i < 8;i++){
            for(int j = 0; j < 8; j++){
                text = board.getSquare(i,j).toString();
                printWriter.append(text+"\n");
            }
        }
    }

    public void close()
    {
        System.out.println("closing");
        if(printWriter!=null)printWriter.close();
        if(scanner!= null)scanner.close();
    }
}
