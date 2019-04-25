package model.characters.actions;

import model.characters.Character;

abstract public class Action {
	
    private Character character;
    
    public Action(Character character){
        this.character = character;
    }
    
    abstract public void performAction(); 	//polymorphism
    
    public void actionFinished(){
        character.nextAction();
    } //the action is removed from the character's actionList, which allows the character to begin his next action (if there is a next action in actionList).

    public Character getCharacter(){
        return character;
    }
}
