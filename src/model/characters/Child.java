package model.characters;

import model.Game;
import model.characters.states.*;
import model.items.Plate;
import model.map.mapObjects.Bench;
import model.map.mapObjects.Wardrobe;
import model.places.School;

public abstract class Child extends Character{

	public Child(String gender) {
		super(gender);
	}

	public void goToSchool() {
		if(!stateInQueue(Studying.class)){
			School school = (School) Game.getInstance().getMap().getClosestPlace(getPos(),School.class);
			getStateQueue().add(new StoringItem(this,66,Plate.class, Wardrobe.class));
			getStateQueue().add(new MovingToObjectByType(this,66, Bench.class,school));
			getStateQueue().add(new Studying(this,66,school));
		}
	}

	@Override
	public void incrementHunger(double i) {
		super.incrementHunger(i);
		if (getHunger()<=70) {
			if(!stateInQueue(Eating.class)){
				eat();
			}
		}
	}

	@Override
	public void incrementHygiene(double i) {
		super.incrementHygiene(i);
		if (getHygiene()<=67) {
			if(!stateInQueue(Washing.class)){
				wash();
			}
		}
	}

	@Override
	public void timePassed() {
		super.timePassed();
		int hours = Game.getInstance().getTime().getHours();
		if(hours>=7 && hours<=16){
			if(!stateInQueue(Studying.class)){
				try{
					goToSchool();
				}catch(Exception e) {}
			}
		}
	}
}
