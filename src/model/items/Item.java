package model.items;

import model.GameObject;
import model.map.Tile;

public abstract class Item extends GameObject {
	protected int price = 0;
	public Item(){
		super();
	}

	public int getPrice(){
		return price;
	}
}
