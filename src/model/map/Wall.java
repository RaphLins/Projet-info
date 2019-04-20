package model.map;

import model.GameObject;

public class Wall extends GameObject implements Obstacle {
    public Wall() {
        super();
        ID = "Wall";
    }
}