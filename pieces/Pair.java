package pieces;

public class Pair{

    private int x;	//the first element of the pair
    private int y;	//the second element of the pair

    public Pair(int v1, int v2)
    {        
	x = v1;
        y = v2;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    // Return string representation of a pair
    // as <first,second> with no spaces
    public String toString()
    {
        return "<" + x + "," + y + ">";
    }

    
    public boolean equals(Object o)
    {
        if (o instanceof Pair) {//checks to make sure o is a pair
            Pair other = (Pair) o;//casts it to a pair
            if ((other.x) == (this.x) && ((other.y) == (this.y))) {//makes sure the first and
                //second elements of both pairs are the same in order to return true
                return true;
            }
        }
        return false;
    }

}
