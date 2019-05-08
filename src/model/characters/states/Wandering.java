package model.characters.states;

import model.Game;
import model.characters.Character;
import model.map.Tile;

import java.util.ArrayList;
import java.util.Random;

public class Wandering extends MovingTo {
    public Wandering(Character character, int groupID) {
        super(character, groupID, null);
        ArrayList<Tile> tilesAround = Game.getInstance().getMap().getNearbyTiles(getCharacter().getPos(),3);
        tilesAround.removeIf(tile -> !tile.isWalkable());
        Tile target = tilesAround.get(new Random().nextInt(tilesAround.size()));
        setTarget(target);
    }

}
