package player;
import pieces.Piece;

public class Player {//creates a player for the chess game
    private String playerNum;
    private int numPoints;
    private boolean color;
    private Graveyard graveyard;

    public Player(boolean color){//the color decides if they are player one or 2,
                                //and the other fields are initialized
        if(!color){
            playerNum = "Player 1";
        }
        else{
            playerNum = "Player 2";
        }
        numPoints = 0;
        graveyard = new Graveyard();
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void addPoints(int numPoints) {
        this.numPoints += numPoints;
    }

    public void removePoints(int numPoints){ this.numPoints -= numPoints; }

    public String getPlayerNum() {
        return playerNum;
    }

    public Graveyard getGraveyard() {
        return graveyard;
    }

	/*

    public void addPieceToGraveyard(Piece p){
        graveyard.add(p);
    }

    */
}
