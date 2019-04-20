package model.characters.actions;

import model.characters.Character;

public class Wash extends Action{
    public Wash(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        getCharacter().incrementHygiene(10.07);
        if(getCharacter().getHygiene()==100){
            System.out.println("finished washing");
            actionFinished();
        }
    }
}
