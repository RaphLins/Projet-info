package model.characters;
import model.map.Tile;

public class BabyWizard extends Baby implements Wizard {

	int magicPower = 50;
	public BabyWizard(Tile pos) {
		super(pos);
	}

	@Override
	public float getMagicPower() {
		return (float)magicPower/100;
	}
}
