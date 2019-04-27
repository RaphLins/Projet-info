package model.items;

import model.map.Tile;

public class Broom extends Item {

	public Broom(int price) {
		super();
		ID = "Broom";
		price = 50;
	}

	@Override
	public int getPrice() {
		return price;
	}
}
