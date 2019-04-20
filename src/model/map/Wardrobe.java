package model.map;

import model.GameObject;
import model.ObjectHolder;
import model.items.Item;
import model.map.Obstacle;
import model.map.Tile;

import java.util.ArrayList;

public class Wardrobe extends Item implements Obstacle, ObjectHolder {

    private ArrayList<GameObject> inventory;
    public Wardrobe() {
        super();
        ID = "Wardrobe";
    }


    @Override
    public ArrayList<GameObject> getInventory() {
        return inventory;
    }
}
