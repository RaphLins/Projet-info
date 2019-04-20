package model.characters;

import model.map.Tile;

public class KidWizard extends Kid implements Wizard {

	int magicPower = 50;

	public KidWizard() {
		super();
	}
	@Override
	public float getMagicPower() {
		return (float)magicPower/100;
	}
}
