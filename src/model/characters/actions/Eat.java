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
        if(getCharacter().getHunger()==100){	//the action is stopped if the value of hunger has reached its maximum.
            System.out.println("finished eating");
            actionFinished();
        }
    }	//polymorphism : in this case, the character's hunger is modified and the character is oriented to the North (logic orientation if he's looking for food in his fridge).
}
