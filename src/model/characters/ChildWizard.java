package model.characters;

import model.map.Tile;

public class ChildWizard extends Kid implements Wizard {

	double magicPower = 50;

	public ChildWizard(String gender) {
		super();
		ID = "Child Wizard ("+gender+")";
	}
	@Override
	public double getMagicPower() {
		return magicPower;
	}

	@Override
	public void incrementMagicPower(double i) {
		magicPower = Math.max(Math.min(magicPower+i,100),0);
	}
}
