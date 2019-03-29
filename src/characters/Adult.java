package src.characters;

import src.places.Place;
import src.view.Tile;
import src.places.WorkablePlace;

public abstract class Adult extends Character {
	
	
	public Adult(Place location, Tile position) {
		super(location , position);
	}
	
	public void work(WorkablePlace place) {
		
	}

}
