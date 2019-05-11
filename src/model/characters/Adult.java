package model.characters;

import model.Game;
import model.characters.states.*;
import model.items.Food;
import model.map.Tile;
import model.map.mapObjects.Stool;
import model.places.WorkablePlace;
import model.places.MinistryOfMagic;
import model.places.Place;
import java.util.ArrayList;
import model.characters.states.MovingToObjectByType;
public abstract class Adult extends Character {


    public Adult(String gender) {
        super(gender);
    }

    public void work() {
    	getStateQueue().add(new MovingToObjectByType((Character)this,66, Stool.class,getClosestWorkPlace(Game.getInstance().getMap().getMinistriesOfMagic())));
    	getStateQueue().add(new Working((Character)this,66));
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

    public void feedBaby(Baby baby){
        getStateQueue().add(new FetchingItem(this,55, Food.class));
        getStateQueue().add(new MovingToObject(this,55,baby));
        getStateQueue().add(new Feeding(this,55,baby));
    }

    @Override
    public void reactToSound(String sound, SoundMaker source) {
        if(source instanceof Baby) {
            Baby baby = ((Baby)source);
            if(baby.getHouse()==getHouse() && sound.equals("I'm hungry!!")) {
                if(!stateInQueue(Feeding.class)){
                    feedBaby(baby);
                }
            }
        }
    }
    public MinistryOfMagic getClosestWorkPlace(ArrayList<MinistryOfMagic> ministriesOfMagic) {
    	ArrayList<Tile> possibleTargets = new ArrayList<>();
    	for(MinistryOfMagic ministry : ministriesOfMagic) {
    		possibleTargets.add(ministry.getPos());
    	}
    	return (MinistryOfMagic) Game.getInstance().getMap().getClosestTile(this.getPos(), possibleTargets).getLocation();
    }
}
