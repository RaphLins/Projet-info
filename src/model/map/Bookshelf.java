package model.map;

import model.GameObject;
import model.items.Item;

import java.util.ArrayList;

public class Bookshelf extends Item  implements Obstacle{
    public Bookshelf() {
        super();
        ID = "Bookshelf";
        price = 50;
        accessDirections = new int[]{SOUTH};
    }
}
