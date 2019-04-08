package model.characters;

import model.map.Tile;

public class KidWizard extends Kid implements Wizard {

	int magicPower = 50;

	public KidWizard(Tile pos) {
		super(pos);
	}
	@Override
	public float getMagicPower() {
		return (float)magicPower/100;
	}
}
