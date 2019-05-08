package model.characters.states;

import model.Game;
import model.GameObject;
import model.characters.Character;
import model.map.Tile;

import java.util.ArrayList;

public class MovingToObject extends MovingTo {
    private Class objectType;

    public MovingToObject(Character character, int groupID, Class objectType) {
        super(character,groupID,null);
        this.objectType = objectType;
    }

    @Override
    public Tile getTarget() {
        return Game.getInstance().getMap().getClosestTile(getCharacter().getPos(), objectType, 50); //finds the closest tile around the character that contains the object needed.
    }

    @Override
    public void finish() {
        ArrayList<GameObject> list  = Game.getInstance().getMap().getNearbyObjects(getCharacter().getPos(), objectType,1);
        if(!list.isEmpty()){
            getCharacter().rotateTo(list.get(0).getPos());//rotate to face the object found
        }
        super.finish();
    }

    public Class getObjectType() {
        return objectType;
    }
}
