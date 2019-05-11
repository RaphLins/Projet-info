package model.characters;

import java.util.ArrayList;

import model.Game;
import model.characters.states.Eating;
import model.characters.states.FetchingItem;
import model.characters.states.MovingToObjectByType;
import model.characters.states.Studying;
import model.characters.states.Washing;
import model.items.Food;
import model.map.mapObjects.Bench;
import model.places.School;
import model.map.Tile;

public abstract class Child extends Character{

	public Child(String gender) {
		super(gender);
	}

	public void goToSchool() {
		getStateQueue().add(new MovingToObjectByType((Character)this,66, Bench.class,getClosestSchool(Game.getInstance().getMap().getSchools())));
    	getStateQueue().add(new Studying((Character)this,66));
	}

	@Override
	public void reactToSound(String sound, SoundMaker source) {

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
	
	public School getClosestSchool(ArrayList<School> schools) {
		ArrayList<Tile> possibleTargets = new ArrayList<>();
    	for(School school : schools) {
    		possibleTargets.add(school.getPos());
    	}
    	return (School) Game.getInstance().getMap().getClosestTile(this.getPos(), possibleTargets).getLocation();
    }
}
