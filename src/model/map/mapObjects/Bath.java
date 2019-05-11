package model.map.mapObjects;

import model.characters.Character;
import model.map.Obstacle;
import model.map.UsableItem;

public class Bath extends UsableItem implements Obstacle {
    public Bath() {
        super();
        setID("Bath");
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
