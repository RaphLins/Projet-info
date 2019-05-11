package model.characters.states;

import model.Game;
import model.characters.Character;
import model.items.Food;
import model.places.Place;

public class FetchingItem extends MovingToObjectByType {
    public FetchingItem(Character character, int groupID, Class type) {
        super(character, groupID, type);
    }

    public FetchingItem(Character character, int groupID, Class type, Place location) {
        super(character, groupID, type,location);
    }

    @Override
    public void init() {
        super.init();
        if(getCharacter().getItem(getObjectType())!=null){//skip this state if the character already has the item
            super.finish();
        }
        else if(getObjectType()== Food.class && getCharacter().getLocation() != getCharacter().getHouse()){
            if(Game.getInstance().spendGold(10)){
                new Food().storeIn(getCharacter());
            }
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
        if(getCharacter().pickUpItem(getObjectType(),getCharacter().getPos())||getCharacter().pickUpItemInFront(getObjectType())){//pick up item once the destination is reached
            //item picked up
        }
        else {
            System.out.println("inventory full");
            cancel();
        }
    }
}