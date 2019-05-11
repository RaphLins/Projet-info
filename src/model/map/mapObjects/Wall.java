package model.map.mapObjects;

import model.items.Item;
import model.map.Obstacle;

public class Wall extends Item implements Obstacle {
    public Wall() {
        super();
        setID("Wall");
        setPrice(10);
    }
}