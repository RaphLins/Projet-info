package model.characters.states;

import model.characters.Character;
import model.map.mapObjects.Toilet;

public class Peeing extends UsingItem {
    public Peeing(Character character, int groupID) {
        super(character, groupID, Toilet.class);
    }

    @Override
    public void init() {
        getCharacter().getLocation();
        super.init();
    }
    @Override
    public void run() {
        getCharacter().incrementBladder(50.8);
        if(getCharacter().getBladder()==100){
            finish();
        }
    }
}
