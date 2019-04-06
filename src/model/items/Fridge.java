package model.items;

import model.GameObject;
import model.ObjectHolder;
import model.map.Tile;

import java.util.ArrayList;

public class Fridge extends Item implements Obstacle, ObjectHolder {
    private ArrayList<GameObject> inventory;
    public Fridge(Tile pos) {
        super(pos);
    }

    public void addFood(Food food){
        inventory.add(food);
    }

    @Override
    public ArrayList<GameObject> getInventory() {
        return inventory;
    }
}
