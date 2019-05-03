package model.characters.states;

import model.characters.Character;

public class Peeing extends UsingItem {
    public Peeing(Character character, int groupID) {
        super(character, groupID,"Toilet");
    }

    @Override
    public void run() {
        getCharacter().incrementBladder(50.8);
        if(getCharacter().getBladder()==100){
            finish();
        }
    }
}
