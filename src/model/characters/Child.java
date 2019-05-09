package model.characters;

import model.Game;
import model.GameObject;
import model.characters.states.Eating;
import model.characters.states.State;
import model.characters.states.Washing;
import model.places.School;
import model.map.Tile;

public abstract class Child extends Character{

	public Child(String gender) {
		super(gender);
	}

	public void goToSchool(School school) {
		
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

	@Override
	public void makeSound(String sound) {
		for(Tile tile: Game.getInstance().getMap().getNearbyTiles(getPos(),40)){
			for(GameObject object:tile.getObjects()){
				if(object instanceof SoundListenner){
					((SoundListenner)object).reactToSound(sound,this);
				}
			}
		}
	}
}
