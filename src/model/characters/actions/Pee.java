package model.characters.actions;

import model.characters.Character;
import model.characters.Directable;

public class Pee extends Action {
    public Pee(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        getCharacter().incrementBladder(50.8);
        getCharacter().setDirection(Directable.NORTH); //so that the character looks like he's really peeing in the toilet.
        if(getCharacter().getBladder()==100){
            System.out.println("finished peeing");
            actionFinished();
        }
    }	//polymorphism : the character's bladder is modified.
}
