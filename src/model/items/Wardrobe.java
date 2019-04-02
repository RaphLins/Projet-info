package model.items;

import model.map.Tile;

public class Wardrobe extends Item implements Obstacle {
    public Wardrobe(Tile pos) {
        super(pos);
        ID = "wardrobe";
    }
}
