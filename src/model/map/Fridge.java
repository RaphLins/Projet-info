package model.map;

import model.GameObject;
import model.ObjectHolder;
import model.items.Food;
import model.items.Item;

import java.util.ArrayList;

public class Fridge extends Item implements Obstacle, ObjectHolder {
    private ArrayList<GameObject> inventory = new ArrayList<>();
    public Fridge() {
        super();
        ID = "Fridge";
        price = 50;
    }
    public void removeItem(GameObject item){
        inventory.remove(item);
    }
    public void addFood(Food food){
        inventory.add(food);
    }

    @Override
    public ArrayList<GameObject> getInventory() {
        return inventory;
    }
    @Override
    public void addItem(GameObject item) {
    	inventory.add(item);
    	item.removeFromMap();
    }
}
