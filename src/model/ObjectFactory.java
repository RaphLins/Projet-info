package model;

import model.GameObject;
import model.Tile;
import model.items.HouseWindow;
import model.items.Wall;

public class ObjectFactory {
    public GameObject getInstance(String type, Tile tile){
        GameObject res = null;
        switch(type) {
            case "X": res = new Wall(tile); break;
            case "F": res = new HouseWindow(tile); break;
        }
        return res;
    }
}
