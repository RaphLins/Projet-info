package model.characters;

import model.Game;
import model.GameObject;
import model.ObjectHolder;
import model.ObjectWithActions;
import model.Time;
import model.TimeObserver;
import model.characters.states.*;
import model.items.HoldableItem;
import model.items.Plate;
import model.map.*;
import model.places.House;
import model.places.Place;

import java.lang.Math;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Character extends GameObject implements Directable, ObjectHolder, TimeObserver,ObjectWithActions{
	private double hunger = 100;
	private double hygiene = 60;
	private double bladder = 100;
	private double energy = 100;
	private double happiness = 50;
	private Place location;
	private House house;
	private int direction = EAST;
	private ArrayList<HoldableItem> inventory = new ArrayList<>();

	private LinkedList<State> stateQueue = new LinkedList<>();
	//allows to easily have state sequences.

	public Character() {
		Game.getInstance().getTime().attach(this);
	}
	//the character will be affected for every time's loop.

	public void goTo(Tile target){
		if(target.isWalkable()){
            stateQueue.add(new MovingTo(this,0, target));
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
		getPos().update();	//the method rotateTo may be used several times for long motions (in which the character's tile is constantly changing).
	}
	
	@Override
	public int getDirection() {
		return direction;
	}

	public void eat() {
        stateQueue.add(new FetchingItem(this,1, Plate.class));
        stateQueue.add(new MovingToObject(this,1, Stool.class));
        stateQueue.add(new Eating(this,1));
        stateQueue.add(new StoringItem(this,1,Plate.class, Wardrobe.class));
	}

	public void wash() {
        stateQueue.add(new MovingToObject(this,2, Bath.class));
        stateQueue.add(new Washing(this,2));
	}

	public void pee() {
        stateQueue.add(new MovingToObject(this,3, Toilet.class));
        stateQueue.add(new Peeing(this,3));
	}

	public void sleep() {
        stateQueue.add(new MovingToObject(this,4, Bed.class));
        stateQueue.add(new Sleeping(this,4));
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

	public double getHappiness() {
		return happiness;
	}

	public boolean carryItem(HoldableItem item){
		if(inventory.size()<=9){
			item.storeIn(this);
			return true;
		}
		else {
			//inventory full
			return false;
		}
	}

	public ArrayList<HoldableItem> getInventory() {
		return inventory;
	}

	public void incrementBladder(double i) {
		bladder = Math.max(Math.min(bladder+i,100),0);	//changes the bladder's value by i (i can be positive if for example the character is peeing, or negative in other cases).
		//even if the character has to pee, the action can't be done automatically if he's doing something else.
		//the value can't exceed 100 (because of the min) and can't be lower than 0 (because of the max).
		if (bladder<=30) {
		    Boolean bool = true;
		    for(State state : stateQueue){
		        if(state instanceof Peeing){
		            bool=false;
                }
            }
		    if(bool){
                pee();
            }
		}
	}
	
	public void incrementEnergy(double i) {
		energy = Math.max(Math.min(energy+i,100),0);
        if (energy<=30) {
            Boolean bool = true;
            for(State state : stateQueue){
                if(state instanceof Sleeping){
                    bool=false;
                }
            }
            if(bool){
                sleep();
            }
        }
	}
	
	public void incrementHunger(double i) {
		hunger = Math.max(Math.min(hunger+i,100),0);
        if (hunger<=90) {
            Boolean bool = true;
            for(State state : stateQueue){
                if(state instanceof Eating){
                    bool=false;
                }
            }
            if(bool){
                eat();
            }
        }
	}
	
	public void incrementHygiene(double i) {
		hygiene = Math.max(Math.min(hygiene+i,100),0);
        if (hygiene<=50) {
            Boolean bool = true;
            for(State state : stateQueue){
                if(state instanceof Washing){
                    bool=false;
                }
            }
            if(bool){
                wash();
            }
        }
	}

	public void incrementHappiness(double i) {
		happiness = Math.max(Math.min(happiness+i,100),0);
	}
	
	@Override
	public void timePassed() {	//the method timePassed (polymorphism) is used in the run method in the thread time.
		//Everything here is done automatically as the time passes.\
		if(getPos()==null){
			return;//to prevent running before proper initialization
		}

		State currentState = stateQueue.peek();

		if(currentState instanceof Sleeping){
			incrementBladder(-0.2);
		}
		else {
			incrementBladder(-0.4);
		}
		incrementEnergy(-0.14);
		incrementHunger(-0.26);
		incrementHygiene(-0.07);

		if(currentState !=null){
			currentState.performAction();	//polymorphism
		}

		if (this == Game.getInstance().getSelectedObject()) {
			Game.getInstance().getWindow().updateStatus();
			//the StatusView has to evolve as the time passes if a character is selected, because the evolution of his daily needs has to be shown.
		}
	}

	public void nextState() {
		if(!stateQueue.isEmpty()){
			stateQueue.remove();
		}
	}	//the current state is changed, because the first element in stateQueue has changed because of the remove.


	public void cancelAction(){//delete all states with the same group ID as the canceled state
		if(stateQueue.peek()!=null){
			int groupID= stateQueue.peek().getGroupID();
			stateQueue.removeIf(state -> state.getGroupID() == groupID);
		}
	}

	public boolean isDoingNothingImportant(){
		//if(Game.getInstance().getSelectedObject() == this){
		//	return false;
		//}
		return stateQueue.peek() == null;
	}	//returns true if the stateQueue is empty, which also means that the character is doing nothing.

	public Tile getTileInFront(){
		return Game.getInstance().getMap().getTileNextTo(getPos(),direction);
	}

	public void placeItem(HoldableItem item){//place an item from the inventory in front of the character, and stores it if there is an object holder
		Tile tile = getTileInFront();
		if(tile.getTopObject() instanceof ObjectHolder){
			if(item!=null){
				item.storeIn((ObjectHolder) tile.getTopObject());
			}

		}
		else {
			item.setPos(tile);
		}
	}

	public HoldableItem getItem(Class type){//return item of given type from the inventory
		HoldableItem res = null;
		for(HoldableItem item:inventory){
			if(type.isInstance(item)){
				res = item;
			}
		}
		return res;
	}

	public boolean pickUpItem(Class type, Tile tile){//store item of given type on given tile in the inventory
		HoldableItem item = null;
		boolean succes =false;
		for(GameObject object:tile.getObjects()){
			if(object instanceof ObjectHolder && !(object instanceof Character)){
				for(GameObject storedObject : ((ObjectHolder)object).getInventory()) {
					if (type.isInstance(storedObject)) {
						item = (HoldableItem) storedObject;
					}
				}
			}
			if(type.isInstance(object)){
				item = (HoldableItem) object;
			}
		}
		if(item !=null){
			succes = carryItem(item);
		}
		return succes;
	}

	public boolean pickUpItemInFront(Class type) {
		return pickUpItem(type,getTileInFront());
	}

	public void stopEverything(){//cancel all states
		if(!stateQueue.isEmpty()){
			stateQueue.peek().cancel();
		}
		stateQueue.clear();
	}
}