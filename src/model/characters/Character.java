package model.characters;

import model.Game;
import model.GameObject;
import model.places.Place;
import model.map.Tile;

public abstract class Character extends GameObject implements Directable {
	private int hunger = 0;
	private int hygiene = 100;
	private int bladder = 0;
	private int energy = 100;
	private Place location;
	private int direction = EAST;
	private Thread moveThread;

	public Character(Tile pos) {
		super(pos);
	}

	public void goTo(Tile target){
		if(target.isWalkable()){
			if(moveThread!=null)moveThread.stop();
			moveThread = new Thread(new AStarThread(Game.getInstance(),this, target, Game.getInstance().getMap()));
			moveThread.start();
		}
		else{
			rotate(target.getX()-getPosX(),target.getY()-getPosY());
		}
	}

	public void move(int x, int y){
		move(Game.getInstance().getMap().getTileAt(getPosX()+x,getPosY()+y));
	}

	public void rotate(int x, int y) {
		if(y<x && y<-x)
			direction = NORTH;
		else if(y>x && y>-x)
			direction = SOUTH;
		else if(y<x && y>-x)
			direction = EAST;
		else if(y>x && y<-x)
			direction = WEST;
		getPos().update();
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

	public void fetchItem() {

	}
	public double getEnergy() {
		return energy/100.0;
	}

}
