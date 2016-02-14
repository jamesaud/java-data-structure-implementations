package FloodIt;

import java.util.LinkedList;
import java.util.List;

public class FloodFunction {
    private Driver driver;
    //here floodList is declared as a LinkedList
    //it is declared as public (a bad practice), but it is needed by the Driver class
    public List<Coord> floodList = new LinkedList<Coord>();
    
    //constructor  
    public FloodFunction(Driver newDriver) {
        driver = newDriver;
        //when the game starts, only the cell at the left/top corner is filled
        floodList.add(new Coord(0, 0));
       // floodList.add(new Coord(1, 0));
      //  floodList.add(new Coord(2, 0));
    }
    
    //checks if the coord exists
    public boolean dir_check(Coord coord){
      if (coord.y < 0 | coord.y >= driver.size | coord.x < 0 | coord.x >= driver.size){return false;}
      return true;
    }
    
    //checks if coordinate in floodList
    public boolean in_floodList(Coord coord){
      for (int i=0; i<floodList.size(); i++){
        if (coord.equals(floodList.get(i))) {return true;}
      }
      return false;
    }
    
    //changes color of coord if it is the same as the one next to it
    public void change_color(Coord curr_coord, Coord next_coord){
      if (! dir_check(next_coord)){ return;}
      else if (colorOfCoord(next_coord)!=colorOfCoord(curr_coord)){return;}
      else if (in_floodList(next_coord)){return;}
      else{floodList.add(next_coord);}    
    }
    //flood function is to be implemented by students
    public void flood(int colorToFlood) {
      for (int i = 0; i < floodList.size(); i++){
        Coord curr = floodList.get(i);
        //get the surrounding blocks
        Coord d = down(curr);
        Coord l = left(curr);
        Coord r = right(curr);
        Coord u = up(curr);
        
        //call the change_color function, which determines if the blocks should be changed and adds it to the floodList
        change_color(curr, l); 
        change_color(curr, u);
        change_color(curr, r); 
        change_color(curr, d);
        }
      }
   

    //is input cell (specified by coord) on the board?
    public boolean inbound(final Coord coord) {
        final int x = coord.x;
        final int y = coord.y;
        final int size = this.driver.size;
        return x > -1 && x < size && y > -1 && y < size;
    }
    //return the coordinate of the cell on the top of the given cell (coord) 
    public Coord up(final Coord coord) {
        return new Coord(coord.x, coord.y-1);
    }
    //return the coordinate of the cell below the given cell (coord)
    public Coord down(final Coord coord) {
        return new Coord(coord.x, coord.y+1);
    }
    //return the coordinate of the cell to the left of the given cell (coord)
    public Coord left(final Coord coord) {
        return new Coord(coord.x-1, coord.y);
    }
    //return the coordinate of the cell to the right of the given cell (coord)
    public Coord right(final Coord coord) {
        return new Coord(coord.x + 1, coord.y);
    }
    //get the color of a cell (coord)
    public int colorOfCoord(final Coord coord) {
        return this.driver.getColor(coord);
    }
}
