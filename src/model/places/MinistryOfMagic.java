package model.places;

import model.map.Map;
import model.map.Tile;

public class MinistryOfMagic extends WorkablePlace {

	public MinistryOfMagic(Tile pos, int height, int width, Map map) {
		super(pos, height, width, map);
		//for (Tile tile : getArea())System.out.println(tile.isWalkable());
	}
}
