package model.map.mapObjects;

import model.map.GameObject;
import model.map.ObjectHolder;
import model.items.HoldableItem;
import model.items.Item;
import model.map.Obstacle;

import java.util.ArrayList;

public class Wardrobe extends Item implements Obstacle, ObjectHolder {

    private ArrayList<HoldableItem> inventory = new ArrayList<>();
    public Wardrobe() {
        super();
        setID("Wardrobe");
        setPrice(50);
        setAccessDirections(new int[]{SOUTH});
    }

    @Override
    public ArrayList<HoldableItem> getInventory() {
        return inventory;
    }
}
