package model.characters.states;

import model.Game;
import model.GameObject;
import model.characters.Character;
import model.map.Tile;

public class MovingToObject extends MovingTo {
    private GameObject target;

    public MovingToObject(Character character, int groupID, GameObject target) {
        super(character, groupID, null);
        this.target = target;
    }

    @Override
    public Tile getTarget() {
        return Game.getInstance().getMap().getClosestTile(getCharacter().getPos(),target.getAccessTiles());
    }
}
