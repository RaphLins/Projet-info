package model.characters.states;

import model.characters.Character;
import model.map.mapObjects.Bed;

public class Sleeping extends UsingItem {
    public Sleeping(Character character, int groupID) {
        super(character,groupID, Bed.class);
    }

    @Override
    public void run() {
        getCharacter().incrementEnergy(0.13);
        if(getCharacter().getEnergy()==100){
            finish();
        }
    }
}
