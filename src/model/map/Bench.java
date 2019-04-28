package model.map;

import model.items.Item;

public class Bench extends Item implements Obstacle {
    public Bench() {
        super();
        ID = "Bench";
        price = 50;
    }
}
