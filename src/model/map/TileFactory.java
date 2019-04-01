package model.map;

import model.items.Broom;

public class TileFactory {
    public Tile getInstance(String type, int x, int y){
        Tile res = new Tile(x,y);
        if(!type.isEmpty())res = new Floor(res);
        switch(type) {
            case "X": res = new Wall(res); break;
            case "F": res = new HouseWindow(res); break;
            case "Br": res.addObject(new Broom(40,res));
        }
        return res;
    }
}
