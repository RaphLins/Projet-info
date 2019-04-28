package model.characters.actions;

import model.Game;
import model.characters.Character;
import model.characters.Directable;
import model.map.Bed;

public class Sleep extends Action{
    Bed bed;
    public Sleep(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        if(bed==null){
            bed = (Bed)Game.getInstance().getMap().getNearbyObject(getCharacter().getPos(),"Bed",1);
            if(bed==null){
                actionFinished();
                System.out.println("No bed");
                return;
            }
            else {
                System.out.println("started sleeping");
                bed.sleep(getCharacter());
            }
        }
        getCharacter().incrementEnergy(0.35);
        if(getCharacter().getEnergy()==100){
            System.out.println("finished sleeping");
            bed.wakeUp();
            actionFinished();
        }
    }	//polymorphism : the value of the character's energy is modified.
}
