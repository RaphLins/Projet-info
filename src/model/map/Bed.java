package model.map;

import model.GameObject;
import model.characters.Character;
import model.characters.Directable;
import model.items.Item;

import java.util.ArrayList;
import java.util.Random;

public class Bed extends Item implements Obstacle{
    Character character = null;
    public Bed() {
        super();
        ID = "Bed";
        height = 2;
        price = 50;
    }

    @Override
    public ArrayList<Tile> getAccessTiles() {
        if(character!=null)return new ArrayList<>(); //so it can't be accessed while used
        else return super.getAccessTiles();
    }

    public void sleep(Character character){
        character.setPos(getPos());
        character.setDirection(Directable.SOUTH);	//so that the character looks like he's sleeping in the bed.
        this.character = character;
    }

    public void wakeUp(){
        ArrayList<Tile> tiles = super.getAccessTiles();
        if(tiles.size()!=0){
            character.setPos(tiles.get(new Random().nextInt(tiles.size()))); //return random access tile
        }
    }
}
