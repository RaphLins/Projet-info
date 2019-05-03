package model.characters.states;

import model.characters.Character;

public class FetchingItem extends MovingToObject {
    private String type;

    public FetchingItem(Character character, int groupID, String type) {
        super(character, groupID, type);
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
        getCharacter().pickUpItem(type,getCharacter().getPos());//pick up item once the destination is reached
        getCharacter().pickUpItemInFront(type);//pick up item once the destination is reached
    }
}