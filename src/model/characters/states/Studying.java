package model.characters.states;

import model.Game;
import model.GameObject;
import model.characters.Child;
import model.characters.Character;
import model.characters.Wizard;
import model.map.Bench;
import model.map.Tile;

public class Studying extends State {
	
	public Studying(Character character,int groupID) {
		super(character, groupID);
	}
	
	 @Override
	 public void init() {
		 super.init();
		 Bench bench = null;
		 for(int i = 0;i<4;i++){
			 Tile tile = Game.getInstance().getMap().getTileNextTo(getCharacter().getPos(),i);
			 for(GameObject object:tile.getObjects()){
				 if(Bench.class.isInstance(object)){
					 bench = (Bench) object;
					 getCharacter().rotateTo(tile);
					 return;
				 }
			 }
		 }
		 if(bench==null){
			 cancel();
		 }
	 }
	 
	 @Override
	 public void run() {
		 getCharacter().incrementHappiness(-0.03);
		 if(getCharacter() instanceof Wizard) {
			 ((Wizard)getCharacter()).incrementMagicPower(0.015);
		 }
		 if(getCharacter().getHygiene()<=20 || getCharacter().getEnergy()<=20) {
			 finish();
		 }
		 if(getCharacter().getBladder()<=20) {
			 getCharacter().stopEverything();
			 getCharacter().pee();
			 ((Child)getCharacter()).goToSchool();
		 }
		 if(getCharacter().getHunger()<=20) {
			 getCharacter().stopEverything();
			 getCharacter().eat();
			 ((Child)getCharacter()).goToSchool();
		 }
	 }


}
