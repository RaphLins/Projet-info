package model.map;

import model.characters.Character;
import model.items.Item;

import java.util.ArrayList;
import java.util.Random;

abstract public class UsableItem extends Item {
    private Character user = null;

    public boolean use(Character user) {
        synchronized (user){
            if(this.user !=null){
                return false; //return false if failed
            }
            this.user = user;
            return true;
        }
    }

    @Override
    public ArrayList<Tile> getAccessTiles() {
        if(user !=null){
            return new ArrayList<>();//to prevent other using it
        }
        else {
            return super.getAccessTiles();
        }
    }

    public void stopUsing() {
        ArrayList<Tile> tiles = super.getAccessTiles();
        if(tiles.size()!=0){
            user.setPos(tiles.get(new Random().nextInt(tiles.size()))); //return random access tile
        }
        user =null;
    }
}