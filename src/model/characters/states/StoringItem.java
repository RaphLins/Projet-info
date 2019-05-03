package model.characters.states;

import model.characters.Character;

public class StoringItem extends MovingToObject {
    private String type;
    public StoringItem(Character character, int groupID, String type, String containerType) {
        super(character,groupID,containerType);
        this.type = type;
    }

    @Override
    public void finish() {
        super.finish();
        getCharacter().placeItem(getCharacter().getItem(type));
    }
}