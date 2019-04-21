package model.map;

import model.GameObject;

import java.util.ArrayList;

public class Bed extends GameObject {
    public Bed() {
        super();
        ID = "Bed";
        height = 2;
    }
    @Override
    public ArrayList<Tile> getAccessTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(getPos());
        return tiles;
    }
}
