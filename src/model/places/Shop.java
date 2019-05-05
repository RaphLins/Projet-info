package model.places;

import model.items.Item;
import model.map.Map;
import model.map.Tile;

import java.util.ArrayList;

public class Shop extends WorkablePlace {
	
	private ArrayList<Item> items = new ArrayList<Item>();


	public Shop(Tile pos, int height, int width, Map map) {
		super(pos, height, width, map);
	}
}
