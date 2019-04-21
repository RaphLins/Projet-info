package model.characters.actions;

import model.characters.Character;
import model.characters.Directable;

public class Wash extends Action{
    public Wash(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        getCharacter().incrementHygiene(10.07);
        getCharacter().setDirection(Directable.SOUTH);
        if(getCharacter().getHygiene()==100){
            System.out.println("finished washing");
            actionFinished();
        }
    }
}
