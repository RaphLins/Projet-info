package model.characters.states;

import model.characters.Character;
import model.places.Place;

public class StoringItem extends MovingToObjectByType {
    private Class type;
    public StoringItem(Character character, int groupID, Class type, Class containerType) {
        super(character,groupID,containerType);
        this.type = type;
    }

    public StoringItem(Character character, int groupID, Class type, Class containerType, Place location) {
        super(character,groupID,containerType, location);
        this.type = type;
    }

    @Override
    public void finish() {
        super.finish();
        getCharacter().placeItem(getCharacter().getItem(type));
    }
}