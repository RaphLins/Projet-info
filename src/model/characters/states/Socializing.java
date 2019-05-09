package model.characters.states;

import model.characters.Character;

public class Socializing extends Waiting {
    private Character acquaintance;

    public Socializing(Character character, int groupID, Character acquaintance) {
        super(character, groupID, 50);
        this.acquaintance = acquaintance;
    }

    @Override
    public void run() {
        super.run();
        getCharacter().incrementHappiness(getCharacter().getRelationLevel(acquaintance)*2);
        acquaintance.incrementHappiness(acquaintance.getRelationLevel(acquaintance)*2);
    }

    @Override
    public void finish() {
        super.finish();
        getCharacter().increaseRelationLevel(acquaintance);
        acquaintance.increaseRelationLevel(getCharacter());
    }
}
