package model.characters.states;

import model.characters.Character;
import model.map.Tile;
import model.places.Place;

import java.util.ArrayList;
import java.util.Random;

public class Wandering extends MovingTo {
    public Wandering(Character character, int groupID) {
        super(character, groupID, null);
        Place location = getCharacter().getPos().getLocation();
        if(location == getCharacter().getHouse()){//walks to a random tile nearby if already in the house
            ArrayList<Tile> tiles = new ArrayList<>(location.getArea());
            tiles.removeIf(tile->!tile.isWalkable());
            tiles.removeIf(tile->tile.distanceTo(getCharacter().getPos())>5);
            if(!tiles.isEmpty()){
                setTarget(tiles.get(new Random().nextInt(tiles.size())));
            }
        }
    }

}
