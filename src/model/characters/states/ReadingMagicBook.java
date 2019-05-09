package model.characters.states;

import model.characters.Character;
import model.characters.Wizard;

public class ReadingMagicBook extends Waiting {
    private Wizard wizard;

    public ReadingMagicBook(Wizard wizard, int groupID) {
        super((Character) wizard, groupID, 10);
        this.wizard = wizard;
    }

    @Override
    public void run() {
        super.run();
        wizard.incrementMagicPower(1);
    }
}
