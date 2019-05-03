package model.characters;

import model.map.Tile;

public class AdultWizard extends Adult implements Wizard {
    private double magicPower = 50;
    public AdultWizard(String gender) {
        super();
        ID = "Adult Wizard ("+gender+")";
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
