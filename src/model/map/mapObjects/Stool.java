package model.map.mapObjects;

import model.characters.Character;
import model.map.Obstacle;
import model.map.UsableItem;

public class Stool extends UsableItem implements Obstacle {
    public Stool() {
        super();
        setID("Stool");
        setPrice(50);
    }

    @Override
    public boolean use(Character character) {
        boolean success = super.use(character);
        if(success){
            character.setPos(getPos());
            character.setDirection(SOUTH);
        }
        return success;
    }

}
