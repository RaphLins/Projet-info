package model.characters.actions;

import model.characters.Character;

public class Eat extends Action{
    public Eat(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        getCharacter().incrementHunger(5.24);
        if(getCharacter().getHunger()==100){
            System.out.println("finished eating");
            actionFinished();
        }
    }
}
