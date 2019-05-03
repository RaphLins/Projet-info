package model.map;

import model.GameObject;
import model.ObjectHolder;
import model.items.HoldableItem;
import model.items.Item;
import model.map.Obstacle;
import model.map.Tile;

import java.util.ArrayList;

public class Wardrobe extends Item implements Obstacle, ObjectHolder {

    private ArrayList<HoldableItem> inventory = new ArrayList<>();
    public Wardrobe() {
        super();
        ID = "Wardrobe";
        price = 50;
        accessDirections = new int[]{SOUTH};
    }
    public void removeItem(GameObject item){
        inventory.remove(item);
    }

    @Override
    public ArrayList<HoldableItem> getInventory() {
        return inventory;
    }
}
