package model.map.mapObjects;

import model.map.ObjectHolder;
import model.items.HoldableItem;
import model.items.Item;
import model.map.Obstacle;

import java.util.ArrayList;

public class Bookshelf extends Item  implements Obstacle, ObjectHolder {
    ArrayList<HoldableItem> inventory = new ArrayList<>();
    public Bookshelf() {
        super();
        setID("Bookshelf");
        setHeight(2);
        setPrice(50);
        setAccessDirections(new int[]{SOUTH});
    }

    @Override
    public ArrayList<HoldableItem> getInventory() {
        return inventory;
    }
}
