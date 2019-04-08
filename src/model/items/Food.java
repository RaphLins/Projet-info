package model.items;

import model.GameObject;
import model.map.Tile;

public class Food extends Item implements CarriableItem {
    public Food(Tile pos) {
        super(pos);
    }
}