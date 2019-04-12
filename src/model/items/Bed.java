package model.items;

import model.map.Tile;

public class Bed extends Item implements Obstacle{
    public Bed(Tile pos) {
        super(pos);
        ID = "Bed";
    }
}
