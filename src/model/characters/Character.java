package model.characters;

import model.Game;
import model.GameObject;
import model.ObjectHolder;
import model.items.CarriableItem;
import model.places.Place;
import model.map.Tile;

import java.util.ArrayList;

public abstract class Character extends GameObject implements Directable, ObjectHolder {
	private int hunger = 100;
	private int hygiene = 100;
	private int bladder = 100;
	private int energy = 100;
	private Place location;
	private int direction = EAST;
	private MovingThread moveThread;
	private ArrayList<GameObject> inventory = new ArrayList<>();

	public Character(Tile pos) {
		super(pos);
	}

	public void goTo(Tile target){
		if(target.isWalkable()){
			if(moveThread!=null){
				moveThread.terminate();
			}
			moveThread = new MovingThread(this, target,5);
			Thread thread = new Thread(moveThread);
			thread.start();
		}
		else{
			rotateTo(target);
		}
	}

	public void rotateTo(Tile target) {
		int x= target.getX()-getPosX();
		int y= target.getY()-getPosY();
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



	public void setDirection(int direction) {
		this.direction=direction;
	}

	public float getEnergy() {
		return (float)energy/100;
	}

	public float getHunger() {
		return (float)hunger/100;
	}

	public float getBladder() {
		return (float)bladder/100;
	}

	public float getHygiene() {
		return (float)hygiene/100;
	}

	public void carryItem(CarriableItem item){
		inventory.add((GameObject) item);
	}

	public ArrayList<GameObject> getInventory() {
		return inventory;
	}
}
