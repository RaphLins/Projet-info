package model.items;

import model.map.Tile;

public class Wand extends Item implements CarriableItem, Sellable {
	
	private int price = 50;

    public Wand() {
        super();
        ID = "Wand";
    }

    @Override
    public int getPrice() {
        return price;
    }
}
