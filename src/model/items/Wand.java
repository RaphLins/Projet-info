package model.items;

import model.tiles.Tile;

public class Wand extends SellableItem {
	
	private int price;


    public Wand(int price, Tile pos) {
        super(price, pos);
    }
}
