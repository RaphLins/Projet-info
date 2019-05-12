package model.items;

import model.Game;
import model.map.GameObject;
import model.map.ObjectHolder;
import model.map.Tile;

import java.util.ArrayList;

public abstract class HoldableItem extends Item {// those items can be stored in inventories.
    private ObjectHolder container = null;

    @Override
    public ArrayList<Tile> getAccessTiles() {
        if(container==null){
            return super.getAccessTiles();
        }
        else return ((GameObject)container).getAccessTiles();
    }

    @Override
    public Tile getPos() {
        if(container==null){
            return super.getPos();
        }
        else return ((GameObject)container).getPos();
    }

    public boolean storeIn(ObjectHolder container){
        if(container.getInventory().size()<9){
            removeFromMap();
            container.getInventory().add(this);
            if(Game.isStarted() && Game.getInstance().getSelectedObject() == container){
                Game.getInstance().getWindow().updateInventory();//update inventory if it is currently shown on screen
            }
            this.container = container;
            return true;
        }
        else return false;
    }

    @Override
    public void removeFromMap() {
        super.removeFromMap();
        if(container!=null){
            container.getInventory().remove(this);
            if(Game.isStarted() && (Game.getInstance().getSelectedObject() == this.container)){
                Game.getInstance().getWindow().updateInventory();//update inventory if it is currently shown on screen
            }
        }
        container = null;
    }
}
