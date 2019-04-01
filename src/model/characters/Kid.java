package model.characters;

import model.places.School;
import model.map.Tile;

public abstract class Kid extends Character{

	public Kid(Tile pos) {
		super(pos);
	}

	public void goToSchool(School school) {
		
	}

}
