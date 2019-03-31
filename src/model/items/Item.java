package model.items;

import model.GameObject;
import model.tiles.Tile;

public abstract class Item extends GameObject {

	public Item(Tile pos) {
		super(pos);
	}
}
