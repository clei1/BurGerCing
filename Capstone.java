public class Capstone extends Piece{

    /*~~~~~~~~~~~~~OVERLOADED CONSTRUCTOR~~~~~~~~~~~~~*/
    public Capstone(int color, int x, int y){
	super(color,x,y);
    }

    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    public void flattenWall(Board b, Piece s,int row, int column){
	s.wallToStone(); //fix to ONLY work with stones that are walls, not any piece
    }
    /*~~~~~~~~~~~~~OVERLOADED TOSTRING~~~~~~~~~~~~~*/
    public String toString(){
	String s;
	if (color == 0) {
	    s = "BLACK CAPSTONE";
	}
	else if (color == 1) {
	    s = "WHITE CAPSTONE";
	}
	else {
	    throw new IllegalArgumentException("Not a valid color.");
	}
	return s;
    }
}
