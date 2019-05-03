package model.items;

import model.map.Tile;

public class Broom extends HoldableItem {

	public Broom() {
		super();
		ID = "Broom";
		price = 50;
	}

	@Override
	public int getPrice() {
		return price;
	}
}
