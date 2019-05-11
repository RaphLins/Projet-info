package model.map.mapObjects;

import model.items.Item;
import model.map.Obstacle;

public class Bench extends Item implements Obstacle {
    public Bench() {
        super();
        setID("Bench");
        setHeight(2);
        setPrice(50);
        setAccessDirections(new int[]{SOUTH});
    }
}
