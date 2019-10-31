package pieces;
import java.awt.Color;

public class Square{
  private String locString;
  private int locInt;
  private Color display;
  private Color color;
  private boolean occupied;
  
  public Square(int num, String name, Color c){
    locInt = num;
    locString = name;
    color = c;
    //this is a test for committing
  }
  
  public void occupy(){
    occupied = true; 
  }
  
  public void vacate(){
    occupied = false; 
  }
  
  public void highlight(){
    if(!occupied){
      display = Color.green;
    }
    else{
      display = Color.red;
    }
  }
  
  public void unhighlight(){
     display = color; 
  }
  
  
}
