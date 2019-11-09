package pieces;

public class Pair{

    private int row;	//the first element of the pair
    private int col;	//the second element of the pair

    public Pair(int v1, int v2)
    {        
	row = v1;
        col = v2;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    // Return string representation of a pair
    // as <first,second> with no spaces
    public String toString()
    {
        return "<" + row + "," + col + ">";
    }

    
    public boolean equals(Object o)
    {
        if (o instanceof Pair) {//checks to make sure o is a pair
            Pair other = (Pair) o;//casts it to a pair
            if ((other.row) == (this.row) && ((other.col) == (this.col))) {//makes sure the first and
                //second elements of both pairs are the same in order to return true
                return true;
            }
        }
        return false;
    }

}
