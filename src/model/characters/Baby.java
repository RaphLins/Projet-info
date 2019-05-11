package model.characters;

import model.characters.states.State;
import model.characters.states.Waiting;

public abstract class Baby extends Character {


    public Baby(String gender) {
        super(gender);
        setSpeed(4);
    }

    public void cryForFood() {
        makeSound("I'm hungry!!");
        getStateQueue().add(new Waiting(this,15,30));
	}

    @Override
    public void reactToSound(String sound, SoundMaker source) {

    }

    @Override
    public void incrementHunger(double i) {
        super.incrementHunger(i);
        if (getHunger()<=70) {
            Boolean bool = true;
            for(State state : getStateQueue()){
                if(state instanceof Waiting){
                    bool=false;
                }
            }
            if(bool){
                cryForFood();
            }
        }
    }

    @Override
    public void incrementHygiene(double i) {
        super.incrementHygiene(100);
    }

}
