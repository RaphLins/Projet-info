package model.characters;

import model.Game;
import model.GameObject;
import model.ObjectHolder;
import model.Time;
import model.TimeObserver;
import model.items.CarriableItem;
import model.places.Place;
import model.map.Tile;

import java.util.ArrayList;

public abstract class Character extends GameObject implements Directable, ObjectHolder, TimeObserver {

	private final int DOING_NOTHING = 0;
	private final int MOVING = 1;
	private final int SLEEPING = 2;
	private final int EATING = 3;
	private final int PEEING = 4;
	private final int WASHING = 5;

	private double hunger = 100;
	private double hygiene = 100;
	private double bladder = 100;
	private double energy = 100;
	private Place location;
	private int direction = EAST;
	private MovingThread moveThread;
	private ArrayList<GameObject> inventory = new ArrayList<>();
	private Time time = Time.getInstance();
	private int state = DOING_NOTHING;

	public Character(Tile pos) {
		super(pos);
		Time.getInstance().attach(this);
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
		state = EATING;
	}

	public void wash() {
		state = WASHING;
	}

	public void pee() {
		state = PEEING;

	}

	public void sleep() {
		state = SLEEPING;
	}

	public void fetchItem() {

	}



	public void setDirection(int direction) {
		this.direction=direction;
	}

	public double getEnergy() {
		return energy;
	}

	public double getHunger() {
		return hunger;
	}

	public double getBladder() {
		return bladder;
	}

	public double getHygiene() {
		return hygiene;
	}

	public void carryItem(CarriableItem item){
		inventory.add((GameObject) item);
	}

	public ArrayList<GameObject> getInventory() {
		return inventory;
	}

	public void incrementBladder(double i) {
		bladder = Math.max(Math.min(bladder+i,100),0);
		if (bladder<=25) {
			pee();
		}
	}
	
	public void incrementEnergy(double i) {
		energy = Math.max(Math.min(energy+i,100),0);
		if (energy<=25) {
			sleep();
		}
	}
	
	public void incrementHunger(double i) {
		hunger = Math.max(Math.min(hunger+i,100),0);
		if (hunger<=25) {
			eat();
		}
	}
	
	public void incrementHygiene(double i) {
		hygiene = Math.max(Math.min(hygiene+i,100),0);
		if (hygiene<=25) {
			wash();
		}
	}
	
	@Override
	public void timePassed() {
		incrementBladder(-0.8);
		incrementEnergy(-0.14);
		incrementHunger(-0.34);
		incrementHygiene(-0.07);
		if (this == Game.getInstance().getSelectedObject()) {
			Game.getInstance().getWindow().getStatusView().redraw();
		}

		switch (state){
			case DOING_NOTHING:
				break;
			case EATING:
				incrementHunger(5.24);
				if(getHunger()==100){
					state = DOING_NOTHING;
				}
				break;
			case WASHING:
				incrementHygiene(10.07);
				if(getHygiene()==100){
					state = DOING_NOTHING;
				}
				break;
			case PEEING:
				incrementBladder(50.8);
				if(getBladder()==100){
					state = DOING_NOTHING;
				}
				break;
			case SLEEPING:
				incrementEnergy(0.35);
				if(getEnergy()==100){
					state = DOING_NOTHING;
				}
				break;
		}
	}

}
