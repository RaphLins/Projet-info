package model.map;

import model.GameObject;
public class Bed extends GameObject implements Obstacle {
    public Bed() {
        super();
        ID = "Bed";
        height = 2;
    }
}
