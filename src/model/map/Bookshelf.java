package model.map;

import model.Game;
import model.GameObject;
import model.ObjectHolder;
import model.items.HoldableItem;
import model.items.Item;

import java.util.ArrayList;

public class Bookshelf extends Item  implements Obstacle , ObjectHolder {
    ArrayList<HoldableItem> inventory = new ArrayList<>();
    public Bookshelf() {
        super();
        ID = "Bookshelf";
        price = 50;
        accessDirections = new int[]{SOUTH};
    }

    @Override
    public ArrayList<HoldableItem> getInventory() {
        return inventory;
    }
}
