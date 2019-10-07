import java.awt.Color;

public class Spot{
  private String locString;
  private int locInt;
  private Color display;
  private Color color;
  private boolean occupied;
  
  public Spot(int num, String name, Color c){
    locInt = num;
    locString = name;
    color = c;
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
