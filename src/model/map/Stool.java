package model.map;

import model.items.Item;

public class Stool extends Item implements Obstacle {
    public Stool() {
        super();
        ID = "Stool";
        price = 50;
    }
}
