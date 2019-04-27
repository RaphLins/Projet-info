package model.characters.actions;

import model.characters.Character;

abstract public class Action {
    private Character character;
    private Action previousAction;
    public Action(Character character){
        this.character = character;
    }
    abstract public void performAction();
    public void actionFinished(){
        character.nextAction();
    }

    public Character getCharacter(){
        return character;
    }

    public void setPreviousAction(Action action){
        previousAction = action;
    }

    public Action getPreviousAction(){
        return previousAction;
    }
}
