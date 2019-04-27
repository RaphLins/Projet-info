package model.map;

import model.GameObject;
import model.items.Item;

import java.util.ArrayList;

public class Bed extends Item {
    public Bed() {
        super();
        ID = "Bed";
        height = 2;
        price = 50;
    }
    @Override
    public ArrayList<Tile> getAccessTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(getPos());
        return tiles;
    }
}
