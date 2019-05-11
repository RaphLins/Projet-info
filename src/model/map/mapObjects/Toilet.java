package model.map.mapObjects;

import model.characters.Character;
import model.map.Obstacle;
import model.map.UsableItem;

public class Toilet extends UsableItem implements Obstacle {
    public Toilet() {
        super();
        setID("Toilet");
        setAccessDirections(new int[]{SOUTH});
    }

    @Override
    public boolean use(Character character) {
        boolean success = super.use(character);
        if(success){
            character.setDirection(NORTH);
        }
        return success;
    }
}
