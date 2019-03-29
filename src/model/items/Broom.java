package model.items;

import model.Tile;

public class Broom extends SellableItem {
	
	private int price;


	public Broom(int price, Tile pos) {
		super(price, pos);
	}
}
