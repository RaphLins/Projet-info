package model.map.mapObjects;

import model.characters.Character;
import model.map.Obstacle;
import model.map.UsableItem;

public class Bed extends UsableItem implements Obstacle {
    public Bed() {
        super();
        setID("Bed");
        setHeight(2);
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
