package model;

import model.items.Broom;
import model.tiles.Floor;
import model.tiles.Tile;
import model.tiles.HouseWindow;
import model.tiles.Wall;

public class TileFactory {
    public Tile getInstance(String type, int x, int y){
        Tile res = new Tile(x,y);
        if(!type.isEmpty())res = new Floor(res);
        System.out.println(type);
        switch(type) {
            case "X": res = new Wall(res); break;
            case "F": res = new HouseWindow(res); break;
            case "Br": res.addObject(new Broom(40,res));
        }
        return res;
    }
}
