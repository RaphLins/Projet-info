package model.map;

import model.GameObject;

public class Decoration extends GameObject {
    public Decoration(String id, int width, int height){
        super();
        this.ID = id;
        this.width = width;
        this.height = height;
    }
    public Decoration(String id){
        super();
        this.ID = id;
    }
}
