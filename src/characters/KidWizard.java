package src.characters;

import src.characters.Kid;
import src.characters.Wizard;
import src.places.Place;
import src.places.Tile;

public class KidWizard extends Kid implements Wizard {
	
	public KidWizard(Place location, Tile position) {
		super(location , position);
	}

}
