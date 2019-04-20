package model.map;

import model.GameObject;
import model.ObjectHolder;
import model.items.Food;
import model.items.Item;

import java.util.ArrayList;

public class Fridge extends Item implements Obstacle, ObjectHolder {
    private ArrayList<GameObject> inventory;
    public Fridge() {
        super();
        ID = "Fridge";
        height = 2;
    }

    public void addFood(Food food){
        inventory.add(food);
    }

    @Override
    public ArrayList<GameObject> getInventory() {
        return inventory;
    }
}
