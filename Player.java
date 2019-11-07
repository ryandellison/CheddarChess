import pieces.Piece;
import java.util.ArrayList;

public class Player {//creates a player for the chess game
    private String playerNum;
    private int numPoints;
    private boolean color;
    private ArrayList<Piece> graveyard;

    public Player(boolean color){//the color decides if they are player one or 2,
                                //and the other fields are initialized
        if(!color){
            playerNum = "Player 1";
        }
        else{
            playerNum = "Player 2";
        }
        numPoints = 0;
        graveyard = new ArrayList<>();
    }


    public int getNumPoints() {
        return numPoints;
    }

    public void addPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    public String getPlayerNum() {
        return playerNum;
    }

    public ArrayList<Piece> getGraveyard() {
        return graveyard;
    }

    public void addPieceToGraveyard(Piece p){
        graveyard.add(p);
    }
}
