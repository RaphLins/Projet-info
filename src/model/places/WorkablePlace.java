package model.places;

import model.items.Plate;
import model.map.GameObject;
import model.map.Map;
import model.map.Tile;
import model.map.mapObjects.Wardrobe;

public abstract class WorkablePlace extends Place {

    public WorkablePlace(Tile pos, int height, int width, Map map) {
        super(pos, height, width, map);
        for (Tile tile : getArea()) {
			for(GameObject o : tile.getObjects()) {
				if(o instanceof Wardrobe) {
					for(int i=0;i<=8;i++) (new Plate()).storeIn((Wardrobe)o);
				}
			}
		}
    }
}
