package model.items;

import model.GameObject;
import model.ObjectHolder;
import model.map.Tile;

import java.util.ArrayList;

public class Wardrobe extends Item implements Obstacle, ObjectHolder {

    private ArrayList<GameObject> inventory;
    public Wardrobe(Tile pos) {
        super(pos);
        ID = "Wardrobe";
    }


    @Override
    public ArrayList<GameObject> getInventory() {
        return inventory;
    }
}
