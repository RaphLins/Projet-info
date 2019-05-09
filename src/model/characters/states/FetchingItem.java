package model.characters.states;

import model.characters.Character;
import model.places.Place;

public class FetchingItem extends MovingToObjectByType {
    private Class type;

    public FetchingItem(Character character, int groupID, Class type) {
        super(character, groupID, type);
        this.type = type;
    }

    public FetchingItem(Character character, int groupID, Class type, Place location) {
        super(character, groupID, type,location);
        this.type = type;
    }

    @Override
    public void init() {
        super.init();
        if(getCharacter().getItem(type)!=null){//skip this state if the character already has the item
            super.finish();
        }
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void finish() {
        super.finish();
        if(getCharacter().pickUpItem(type,getCharacter().getPos())||getCharacter().pickUpItemInFront(type)){//pick up item once the destination is reached
            //item picked up
        }
        else {
            System.out.println("inventory full");
            cancel();
        }
    }
}