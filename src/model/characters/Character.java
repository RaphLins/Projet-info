package model.characters;

import model.Game;
import model.GameObject;
import model.items.Obstacle;
import model.places.Place;
import model.Tile;

public abstract class Character extends GameObject implements Directable, Obstacle {
	private int hunger = 0;
	private int hygiene = 100;
	private int bladder = 0;
	private int energy = 100;
	private Place location;
	private int direction = EAST;

	public Character(Tile pos) {
		super(pos);
	}

	public void move(int x, int y){
		move(Game.instance.getMap().getTileAt(getPosX()+x,getPosY()+y));
	}

	public void rotate(int x, int y) {
		if(x == 0 && y == -1)
			direction = NORTH;
		else if(x == 0 && y == 1)
			direction = SOUTH;
		else if(x == 1 && y == 0)
			direction = EAST;
		else if(x == -1 && y == 0)
			direction = WEST;
	}

	@Override
	public int getDirection() {
		return direction;
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
	public double getEnergy() {
		return energy/100.0;
	}

}
