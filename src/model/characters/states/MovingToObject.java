package model.characters.states;

import model.Game;
import model.map.GameObject;
import model.characters.Character;
import model.map.Tile;

public class MovingToObject extends MovingTo {
    private GameObject target;

    public MovingToObject(Character character, int groupID, GameObject target) {
        super(character, groupID, null);
        this.target = target;
    }

    @Override
    public Tile getTarget() {//return the closest object of given type
        return Game.getInstance().getMap().getClosestTile(getCharacter().getPos(),target.getAccessTiles());
    }
}
