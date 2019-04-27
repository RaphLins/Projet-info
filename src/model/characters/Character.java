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
	//allows to easily have action sequences.

	public Character() {
		Time.getInstance().attach(this);	
	}	
	//the character will be affected for every time's loop.

	public void goTo(Tile target){
		stopEverything();
		if(target.isWalkable()){
			actionList.add(new MoveTo(this, target));
		}
		else{
			rotateTo(target);
		}	
	}
	//if the player commands a character to go somewhere, then the character as to stop what he's doing.

	public void rotateTo(Tile target) {
		int x= target.getX()-getPosX();
		int y= target.getY()-getPosY();
		if(y<x && y<-x)		//it means that the *delta* y is negative AND the vertical distance (absolute value of y) is longer than the horizontal distance .
			direction = NORTH;
		else if(y>x && y>-x)
			direction = SOUTH;
		else if(y<x && y>-x)
			direction = EAST;
		else if(y>x && y<-x)
			direction = WEST;
		getPos().update();	//the method rotateTo may be used several times for long motions (in which the character's tile is constantly changing).
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
	//the character has to go to a fridge first, he won't start eating until he's arrived there.

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
		bladder = Math.max(Math.min(bladder+i,100),0);	//changes the bladder's value by i (i can be positive if for example the character is peeing, or negative in other cases).
			//even if the character has to pee, the action can't be done automatically if he's doing something else.
		//the value can't exceed 100 (because of the min) and can't be lower than 0 (because of the max).
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
	public void timePassed() {	//the method timePassed (polymorphism) is used in the run method in the thead time. 
		//Everything here is done automatically as the time passes.
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
			currentAction.performAction();	//polymorphism
		}

		if (this == Game.getInstance().getSelectedObject()) {
			Game.getInstance().getWindow().updateStatus();
			//the StatusView has to evolve as the time passes if a character is selected, because the evolution of his daily needs has to be shown.
		}
	}

	public void nextAction() {
		Action previousAction = actionList.poll();
		Action currentAction = actionList.peek();
		if(currentAction!=null){
			currentAction.setPreviousAction(previousAction);
		}
	}	//the currentAction is changed in timePassed, because the first element in actionList has changed because of the remove.

	public boolean isDoingNothing(){
		//if(Game.getInstance().getSelectedObject() == this){
		//	return false;
		//}
		return actionList.peek() == null;
	}	//returns true if the actionList is empty, which also means that the character is doing nothing.

	public void stopEverything(){
		actionList.clear();
	}	//removes every elements from the actionList : the character is now doing nothing.
}
