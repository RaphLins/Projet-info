package model.characters.states;

import model.characters.Character;

import java.io.Serializable;

abstract public class State implements Serializable {

    private Character character;
    private int groupID;//used to delete multiple states at once if one is canceled
    private boolean running = false;


    public State(Character character, int groupID){
        this.character = character;
        this.groupID = groupID;
    }

    public void init(){
    }

    abstract public void run();

    public void cancel(){
        getCharacter().cancelAction();//delete all states with the same group ID
    }

    public void finish(){
        character.nextState();
    }

    public void performAction(){
        if(running){
            run();
        }
        else {
            init();
            running = true;
        }
    }

    public Character getCharacter(){
        return character;
    }

    public int getGroupID() {
        return groupID;
    }
}
