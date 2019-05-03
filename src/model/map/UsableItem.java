package model.map;

import model.characters.Character;
import model.items.Item;

import java.util.ArrayList;
import java.util.Random;

abstract public class UsableItem extends Item {
    private Character character = null;

    public boolean use(Character character) {
        synchronized (character){
            if(this.character!=null){
                return false; //return false if failed
            }
            this.character = character;
            return true;
        }
    }

    @Override
    public ArrayList<Tile> getAccessTiles() {
        if(character!=null){
            return new ArrayList<>();//to prevent other using it
        }
        else {
            return super.getAccessTiles();
        }
    }

    public void stopUsing() {
        ArrayList<Tile> tiles = super.getAccessTiles();
        if(tiles.size()!=0){
            character.setPos(tiles.get(new Random().nextInt(tiles.size()))); //return random access tile
        }
        character=null;
    }

    public Character getCharacter() {
        return character;
    }
}