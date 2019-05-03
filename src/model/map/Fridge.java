package model.map;

import model.GameObject;
import model.ObjectHolder;
import model.items.Food;
import model.items.HoldableItem;
import model.items.Item;

import java.util.ArrayList;

public class Fridge extends Item implements Obstacle, ObjectHolder {
    private ArrayList<HoldableItem> inventory = new ArrayList<>();
    public Fridge() {
        super();
        ID = "Fridge";
        price = 50;
        accessDirections = new int[]{SOUTH};
    }
    public void removeItem(GameObject item){
        inventory.remove(item);
    }
    public void addFood(Food food){
        inventory.add(food);
    }

    @Override
    public ArrayList<HoldableItem> getInventory() {
        return inventory;
    }
}
