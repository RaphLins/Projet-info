package model.map;

import model.GameObject;
import model.items.Item;

public class Wall extends Item implements Obstacle {
    public Wall() {
        super();
        ID = "Wall";
        price = 10;
    }
}