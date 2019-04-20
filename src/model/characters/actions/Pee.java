package model.characters.actions;

import model.characters.Character;

public class Pee extends Action {
    public Pee(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        getCharacter().incrementBladder(50.8);
        if(getCharacter().getBladder()==100){
            System.out.println("finished peeing");
            actionFinished();
        }
    }
}
