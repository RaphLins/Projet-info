package model.map;

import model.items.Broom;
import model.items.Wardrobe;

import java.util.ArrayList;

public class TileFactory {
    public Tile getInstance(ArrayList<String> types, int x, int y){
        Tile res = new Tile(x,y);
        for(int i =0;i<types.size();i++){
            switch(types.get(i)) {
                case ".": res = new Floor(res); break;
                case "X": res = new Wall(res); break;
                case "F": res = new HouseWindow(res); break;
                case "Br": res.addObject(new Broom(40,res));
                case "M": res.addObject(new Wardrobe(res));
            }
        }
        return res;
    }
}
