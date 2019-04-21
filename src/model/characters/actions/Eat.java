package model.characters.actions;

import model.characters.Character;
import model.characters.Directable;

public class Eat extends Action{
    public Eat(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        getCharacter().incrementHunger(5.24);
        getCharacter().setDirection(Directable.NORTH);
        if(getCharacter().getHunger()==100){
            System.out.println("finished eating");
            actionFinished();
        }
    }
}
