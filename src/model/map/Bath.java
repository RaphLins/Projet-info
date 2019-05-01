package model.map;

import model.Game;
import model.GameObject;
import model.characters.Character;
import model.characters.Directable;
import model.items.Item;

import java.util.ArrayList;
import java.util.Random;

public class Bath extends Item implements Obstacle {
    Character character = null;
    public Bath() {
        super();
        ID = "Bath";
        height = 2;
        price = 50;
    }
    @Override
    public ArrayList<Tile> getAccessTiles() {
        if(character!=null)return new ArrayList<>(); //so it can't be accessed while used
        else return super.getAccessTiles();
    }

    public void wash(Character character) {
        character.setPos(getPos());
        character.setDirection(Directable.SOUTH); //so that the character looks like he's in the bath.
        this.character = character;
    }

    public void stopWashing() {
        ArrayList<Tile> tiles = super.getAccessTiles();
        if(tiles.size()!=0) {
        	character.setPos(tiles.get(new Random().nextInt(tiles.size()))); //return random access tile
        	character = null;
        }
    }
}
