package model.items;

import model.map.GameObject;

public abstract class Item extends GameObject {
	private int price = 0;
	public Item(){
		super();
	}

	public int getPrice(){
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
