package model.map;

import model.Game;
import model.GameObject;
import model.characters.Character;
import model.items.Item;
import model.map.Obstacle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Toilet extends UsableItem implements Obstacle {
    public Toilet() {
        super();
        ID = "Toilet";
        accessDirections = new int[]{SOUTH};
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
