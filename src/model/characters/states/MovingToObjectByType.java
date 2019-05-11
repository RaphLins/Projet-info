package model.characters.states;

import model.Game;
import model.GameObject;
import model.characters.Character;
import model.map.Tile;
import model.places.Place;

import java.util.ArrayList;

public class MovingToObjectByType extends MovingTo {
    private Class objectType;
    private Place location = null;

    public MovingToObjectByType(Character character, int groupID, Class objectType) {
        super(character,groupID,null);
        this.objectType = objectType;
    }

    public MovingToObjectByType(Character character, int groupID, Class objectType, Place location) {
        super(character,groupID,null);
        this.objectType = objectType;
        this.location = location;
    }

    @Override
    public Tile getTarget() {
        if(location == null){
            return Game.getInstance().getMap().getClosestTile(getCharacter().getPos(), objectType, 50);
        }
        else {
            return Game.getInstance().getMap().getClosestTile(getCharacter().getPos(),objectType,location);
        }
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
