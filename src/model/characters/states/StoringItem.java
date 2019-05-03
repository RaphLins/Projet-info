package model.characters.states;

import model.characters.Character;

public class StoringItem extends MovingToObject {
    private Class type;
    public StoringItem(Character character, int groupID, Class type, Class containerType) {
        super(character,groupID,containerType);
        this.type = type;
    }

    @Override
    public void finish() {
        super.finish();
        getCharacter().placeItem(getCharacter().getItem(type));
    }
}