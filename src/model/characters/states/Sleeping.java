package model.characters.states;

import model.characters.Character;

public class Sleeping extends UsingItem {
    public Sleeping(Character character, int groupID) {
        super(character,groupID,"Bed");
    }

    @Override
    public void run() {
        getCharacter().incrementEnergy(0.35);
        if(getCharacter().getEnergy()==100){
            finish();
        }
    }
}
