package model.items;

import model.map.Tile;

public abstract class SellableItem extends Item {
	
	int price;

    public SellableItem(int price, Tile pos) {
        super(pos);
        this.price = price;
    }
}
