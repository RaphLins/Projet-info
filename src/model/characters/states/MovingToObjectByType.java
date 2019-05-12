package model.characters.states;

import model.Game;
import model.map.GameObject;
import model.characters.Character;
import model.map.Tile;
import model.places.Place;

import java.util.ArrayList;

public class MovingToObjectByType extends MovingToObject {
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
    public void init() {
        if(location==null){
            location=getCharacter().getLocation();
        }
        super.init();
    }

    @Override
    public Tile getTarget() {
        if(location == null){//search in current location if not set
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
            getCharacter().rotateTo(Game.getInstance().getMap().getClosestObject(getCharacter().getPos(),list).getPos());//rotate to face the object found
        }
        super.finish();
    }

    public Class getObjectType() {
        return objectType;
    }
}
