package model.places;

import model.map.Tile;

import java.util.ArrayList;

public abstract class Place {
	private ArrayList<Tile> area = new ArrayList<Tile>();
	private ArrayList<Character> people = new ArrayList<Character>();
	
	public Place() {
		
	}
}
