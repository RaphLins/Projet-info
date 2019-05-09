package model.characters;

import model.Game;
import model.GameObject;
import model.characters.states.*;
import model.items.Food;
import model.map.Tile;
import model.places.WorkablePlace;

public abstract class Adult extends Character {


    public Adult(String gender) {
        super(gender);
    }

    public void work(WorkablePlace place) {
		
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
