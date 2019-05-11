package model.characters;

public class BabyWizard extends Baby implements Wizard {

	double magicPower = 50;
	public BabyWizard(String gender) {
		super(gender);
		setID("Baby Wizard (" + gender + ")");
	}

	@Override
	public double getMagicPower() {
		return (float)magicPower;
	}

	@Override
	public void incrementMagicPower(double i) {
		magicPower = Math.max(Math.min(magicPower+i,100),0);
	}

}
