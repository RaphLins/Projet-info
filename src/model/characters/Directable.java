package model.characters;

public interface Directable {	//game objects that can be oriented in different directions.
    
    public static int EAST = 0;
    public static int NORTH = 1;
    public static int WEST = 2;
    public static int SOUTH = 3;
    
    public int getDirection();

}
