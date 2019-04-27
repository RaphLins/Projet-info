package model.map;

import model.Game;
import model.GameObject;
import model.items.Item;

import java.util.ArrayList;

public class Bath extends Item {
    public Bath() {
        super();
        ID = "Bath";
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
