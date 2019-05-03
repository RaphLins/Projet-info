package model.characters.states;

import model.characters.Character;

public class Washing extends UsingItem {
    public Washing(Character character, int groupID) {
        super(character,groupID,"Bath");
    }

    @Override
    public void run() {
        getCharacter().incrementHygiene(10.07);
        if(getCharacter().getHygiene()==100){
            finish();
        }
    }

}