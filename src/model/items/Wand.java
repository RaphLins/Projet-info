package model.items;

import model.Tile;

public class Wand extends SellableItem {
	
	private int price;


    public Wand(int price, Tile pos) {
        super(price, pos);
    }
}
