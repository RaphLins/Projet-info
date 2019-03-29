package src.characters;

import src.places.Place;
import src.places.School;
import src.view.Tile;

public abstract class Kid extends Character{
	
	public Kid(Place location, Tile position) {
		super(location , position);
	}
	
	
	public void goToSchool(School school) {
		
	}

}
