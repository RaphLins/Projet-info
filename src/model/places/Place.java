package model.places;


import model.map.Map;
import model.map.Tile;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Place implements Serializable {
	private ArrayList<Tile> area = new ArrayList<>();
	private ArrayList<Character> people = new ArrayList<>();
	private Tile pos;
	private int height;
	private int width;
	
	public Place(Tile pos, int height, int width, Map map) {
		this.pos = pos;
		int x = pos.getX();
		int y = pos.getY();
		this.height = height;
		this.width = width;
		for(int i=0 ; i<height ; i++) {
			for (int j=0 ; j<width ; j++) {
				Tile tile = map.getTileAt(x+j, y-i);
				area.add(tile);
				tile.setLocation(this);
			}
		}
	}
	
	public ArrayList<Tile> getArea(){
		return area;
	}
	
	public Tile getPos() {
		return pos;
	}
}
