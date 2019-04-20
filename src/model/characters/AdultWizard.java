package model.characters;

import model.map.Tile;

public class AdultWizard extends Adult implements Wizard {
    int magicPower = 50;
    public AdultWizard() {
        super();
        ID = "Adult Wizard";
    }

    @Override
    public float getMagicPower() {
        return (float)magicPower/100;
    }
}
