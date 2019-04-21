package model.map;

import model.Game;
import model.GameObject;

import java.util.ArrayList;

public class Bath extends GameObject{
    public Bath() {
        super();
        ID = "Bath";
        height = 2;
    }

    @Override
    public ArrayList<Tile> getAccessTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(getPos());
        return tiles;
    }
}
