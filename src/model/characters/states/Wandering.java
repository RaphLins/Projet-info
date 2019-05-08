package model.characters.states;

import model.Game;
import model.characters.Character;
import model.map.Tile;

import java.util.ArrayList;
import java.util.Random;

public class Wandering extends MovingTo {
    int tileWalked = 0;
    public Wandering(Character character, int groupID) {
        super(character, groupID, null);
    }

    @Override
    public Tile getTarget() {
        ArrayList<Tile> tilesAround = Game.getInstance().getMap().getNearbyTiles(getCharacter().getPos(),1);
        tilesAround.removeIf(tile -> !tile.isWalkable());
        Tile target = tilesAround.get(new Random().nextInt(tilesAround.size()));
        return target;
    }

    @Override
    public void run() {
        if(tileWalked<=2){
            super.run();
            tileWalked++;
        }
        else{
            finish();
        }
    }
}
