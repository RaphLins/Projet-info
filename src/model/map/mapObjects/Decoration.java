package model.map.mapObjects;

import model.map.GameObject;
import model.map.Obstacle;

public class Decoration extends GameObject implements Obstacle {
    public Decoration(String id, int width, int height){
        super();
        setID(id);
        setWidth(width);
        setHeight(height);
    }
    public Decoration(String id){
        super();
        setID(id);
    }
}
