package model.map;

import model.GameObject;
import model.characters.Character;
import model.characters.Directable;
import model.items.Item;

import java.util.ArrayList;
import java.util.Random;

public class Bed extends UsableItem implements Obstacle{
    public Bed() {
        super();
        ID = "Bed";
        height = 2;
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
