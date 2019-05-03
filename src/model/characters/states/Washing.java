package model.characters.states;

import model.characters.Character;
import model.map.Bath;

public class Washing extends UsingItem {
    public Washing(Character character, int groupID) {
        super(character,groupID, Bath.class);
    }

    @Override
    public void run() {
        getCharacter().incrementHygiene(10.07);
        if(getCharacter().getHygiene()==100){
            finish();
        }
    }

}