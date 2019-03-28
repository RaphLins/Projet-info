package src.characters;

import src.places.Place;
import src.places.Tile;

public abstract class Character {
	private int hunger = 0;
	private int hygiene = 100;
	private int bladder = 0;
	private int tiredness = 0;
	private Place location;
	private Tile position;
	
	public Character(Place location, Tile position) {
		this.location = location;
		this.position = position;
	}
	
	public void eat() {
		
	}
	
	public void wash() {
		
	}
	
	public void pee() {
		
	}
	
	public void sleep() {
		
	}
	
	public void move() {
		
	}
	
	public void fetchItem() {
		
	}
}
