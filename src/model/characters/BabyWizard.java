package model.characters;
import model.map.Tile;

public class BabyWizard extends Baby implements Wizard {

	int magicPower = 50;
	public BabyWizard() {
		super();
	}

	@Override
	public float getMagicPower() {
		return (float)magicPower/100;
	}
}
