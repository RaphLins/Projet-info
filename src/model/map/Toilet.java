package model.map;

import model.Game;
import model.GameObject;
import model.items.Item;
import model.map.Obstacle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Toilet extends Item implements Obstacle {
    public Toilet() {
        super();
        ID = "Toilet";
    }

    @Override
    public ArrayList<Tile> getAccessTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        Tile below = Game.getInstance().getMap().getTileNextTo(getPos(),Map.SOUTH);
        if(below.isWalkable()){
            tiles.add(below);
        }
        return tiles;
    }
}
