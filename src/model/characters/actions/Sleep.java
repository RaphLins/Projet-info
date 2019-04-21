package model.characters.actions;

import model.characters.Character;
import model.characters.Directable;

public class Sleep extends Action{
    public Sleep(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        getCharacter().incrementEnergy(0.35);
        getCharacter().setDirection(Directable.SOUTH);
        if(getCharacter().getEnergy()==100){
            System.out.println("finished sleeping");
            actionFinished();
        }
    }
}
