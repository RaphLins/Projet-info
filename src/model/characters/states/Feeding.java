package model.characters.states;

import model.Game;
import model.characters.Baby;
import model.characters.Character;
import model.items.Food;
import model.items.HoldableItem;

public class Feeding extends State {

    private Baby baby;

    public Feeding(Character character, int groupID, Baby baby) {
        super(character, groupID);
        this.baby = baby;
    }

    @Override
    public void init() {
        super.init();
        if(baby.getHunger()>70){
            cancel();
        }
    }

    @Override
    public void run() {
        baby.incrementHunger(6);
        if(baby.getHunger()==100){	//the state is stopped if the value of hunger has reached its maximum.
            finish();
        }
    }

    @Override
    public void finish() {//uses the food in inventory
        super.finish();
        if(Game.getInstance().getFamily().contains(getCharacter())) {
            for(HoldableItem item : getCharacter().getInventory()) {
                if(item instanceof Food) {
                    getCharacter().getInventory().remove(item);
                    break;
                }
            }
        }
    }
}
