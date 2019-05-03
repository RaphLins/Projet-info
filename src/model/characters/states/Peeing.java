package model.characters.states;

import model.characters.Character;
import model.map.Toilet;

public class Peeing extends UsingItem {
    public Peeing(Character character, int groupID) {
        super(character, groupID, Toilet.class);
    }

    @Override
    public void run() {
        getCharacter().incrementBladder(50.8);
        if(getCharacter().getBladder()==100){
            finish();
        }
    }
}
