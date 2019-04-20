package model.items;

import model.map.Tile;

public class Broom extends Item implements Sellable {
	
	private int price;


	public Broom(int price) {
		super();
		ID = "Broom";
	}

	@Override
	public int getPrice() {
		return price;
	}
}
