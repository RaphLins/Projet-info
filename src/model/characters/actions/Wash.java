package model.characters.actions;

import model.Game;
import model.characters.Character;
import model.characters.Directable;
import model.map.Bath;
import model.map.Bed;

public class Wash extends Action{
    Bath bath;
    public Wash(Character character) {
        super(character);
    }

    @Override
    public void performAction() {
        if(bath==null){
            bath = (Bath) Game.getInstance().getMap().getNearbyObject(getCharacter().getPos(),"Bath",1);
            if(bath==null){
                actionFinished();
                System.out.println("No bath");
                return;
            }
            else {
                System.out.println("started washing");
                bath.wash(getCharacter());
            }
        }
        getCharacter().incrementHygiene(10.07);
        if(getCharacter().getHygiene()==100){
            System.out.println("finished sleeping");
            bath.stopWashing();
            actionFinished();
        }

    }//polymorphism : the value of the character's hygiene is modified.
}
