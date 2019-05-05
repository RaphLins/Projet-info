package model.places;

import model.map.Map;
import model.map.Tile;

public abstract class WorkablePlace extends Place {
	
	private int salary;

    public WorkablePlace(Tile pos, int height, int width, Map map) {
        super(pos, height, width, map);
    }

    public void work(Character cha) {

	}

}
