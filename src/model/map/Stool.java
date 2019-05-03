package model.map;

import model.Game;
import model.characters.Character;
import model.items.Item;

public class Stool extends UsableItem implements Obstacle {
    public Stool() {
        super();
        ID = "Stool";
        price = 50;
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
