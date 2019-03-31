package model.items;

import model.tiles.Tile;

public class Broom extends SellableItem {
	
	private int price;


	public Broom(int price, Tile pos) {
		super(price, pos);
	}
}
