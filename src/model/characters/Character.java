package model.characters;

import model.Game;
import model.GameObject;
import model.ObjectHolder;
import model.Time;
import model.TimeObserver;
import model.characters.actions.*;
import model.items.CarriableItem;
import model.places.Place;
import model.map.Tile;

import java.lang.Math;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Character extends GameObject implements Directable, ObjectHolder, TimeObserver {
	private double hunger = 100;
	private double hygiene = 100;
	private double bladder = 100;
	private double energy = 100;
	private Place location;
	private int direction = EAST;
	private ArrayList<GameObject> inventory = new ArrayList<>();

	private LinkedList<Action> actionList = new LinkedList<>();

	public Character() {
		Time.getInstance().attach(this);
	}

	public void goTo(Tile target){
		stopEverything();
		if(target.isWalkable()){
			actionList.add(new MoveTo(this, target));
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
		System.out.println("started eating");
		actionList.add(new MoveTo(this, "Fridge"));
		actionList.add(new Eat(this));
	}

	public void wash() {
		System.out.println("started washing");
		actionList.add(new MoveTo(this, "Bath"));
		actionList.add(new Wash(this));
	}

	public void pee() {
		System.out.println("started peeing");
		actionList.add(new MoveTo(this, "Toilet"));
		actionList.add(new Pee(this));
	}

	public void sleep() {
		System.out.println("started sleeping");
		actionList.add(new MoveTo(this, "Bed"));
		actionList.add(new Sleep(this));
	}

	public void setDirection(int direction) {
		this.direction=direction;
		getPos().update();
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

	public void removeItem(GameObject item){
		inventory.remove(item);
	}

	public ArrayList<GameObject> getInventory() {
		return inventory;
	}

	public void incrementBladder(double i) {
		bladder = Math.max(Math.min(bladder+i,100),0);
		if (bladder<=30 && isDoingNothing()) {
			pee();
		}
	}
	
	public void incrementEnergy(double i) {
		energy = Math.max(Math.min(energy+i,100),0);
		if (energy<=30 && isDoingNothing()) {
			sleep();
		}
	}
	
	public void incrementHunger(double i) {
		hunger = Math.max(Math.min(hunger+i,100),0);
		if (hunger<=60 && isDoingNothing()) {
			eat();
		}
	}
	
	public void incrementHygiene(double i) {
		hygiene = Math.max(Math.min(hygiene+i,100),0);
		if (hygiene<=30 && isDoingNothing()) {
			wash();
		}
	}
	
	@Override
	public void timePassed() {
		Action currentAction = actionList.peek();

		if(currentAction instanceof Sleep){
			incrementBladder(-0.2);
		}
		else {
			incrementBladder(-0.4);
		}
		incrementEnergy(-0.14);
		incrementHunger(-0.26);
		incrementHygiene(-0.07);

		if(currentAction !=null){
			currentAction.performAction();
		}

		if (this == Game.getInstance().getSelectedObject()) {
			Game.getInstance().getWindow().getStatusView().redraw();
		}
	}

	public void nextAction() {
		Action previousAction = actionList.poll();
		Action currentAction = actionList.peek();
		if(currentAction!=null){
			currentAction.setPreviousAction(previousAction);
		}
	}

	public boolean isDoingNothing(){
		//if(Game.getInstance().getSelectedObject() == this){
		//	return false;
		//}
		return actionList.peek() == null;
	}

	public void stopEverything(){
		actionList.clear();
	}
}
