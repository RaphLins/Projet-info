package model.characters;

import model.Game;
import model.characters.states.*;
import model.items.Food;
import model.map.mapObjects.Stool;
import model.places.MinistryOfMagic;
import model.places.WorkablePlace;
public abstract class Adult extends Character {


    public Adult(String gender) {
        super(gender);
    }

    public void work() {
        if(!stateInQueue(Working.class)){
            WorkablePlace workablePlace = (WorkablePlace) Game.getInstance().getMap().getClosestPlace(getPos(),MinistryOfMagic.class);
            getStateQueue().add(new MovingToObjectByType(this,66, Stool.class,workablePlace));
            getStateQueue().add(new Working(this,66,workablePlace));
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

    public void feedBaby(Baby baby){
        getStateQueue().add(new FetchingItem(this,55, Food.class));
        getStateQueue().add(new MovingToObject(this,55,baby));
        getStateQueue().add(new Feeding(this,55,baby));
    }

    @Override
    public void reactToSound(String sound, SoundMaker source) {
        super.reactToSound(sound, source);
        if(source instanceof Baby) {
            Baby baby = ((Baby)source);
            if(baby.getHouse()==getHouse() && sound.equals("I'm hungry!!")) {
                if(!stateInQueue(Feeding.class)){
                    feedBaby(baby);
                }
            }
        }
    }

}
