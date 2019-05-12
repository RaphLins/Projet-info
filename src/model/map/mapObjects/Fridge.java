package model.map.mapObjects;

import model.items.HoldableItem;
import model.items.Item;
import model.map.ObjectHolder;
import model.map.Obstacle;

import java.util.ArrayList;

public class Fridge extends Item implements Obstacle, ObjectHolder {
    private ArrayList<HoldableItem> inventory = new ArrayList<>();
    public Fridge() {
        super();
        setID("Fridge");
        setPrice(50);
        setAccessDirections(new int[]{SOUTH});
    }

    @Override
    public ArrayList<HoldableItem> getInventory() {
        return inventory;
    }
}
