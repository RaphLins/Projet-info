package model.characters.states;

import model.map.Stool;
import model.map.Table;
import model.map.Tile;
import model.Game;
import model.GameObject;
import model.characters.Character;
import model.items.Food;
import model.items.Plate;
import model.characters.Adult;

public class Working extends UsingItem{
	
	public Working(Character character,int groupID) {
		super(character, groupID, Stool.class);
	}
	
	 @Override
	 public void init() {
		 super.init();
		 Table table = null;
		 for(int i = 0;i<4;i++){
			 Tile tile = Game.getInstance().getMap().getTileNextTo(getCharacter().getPos(),i);
			 for(GameObject object:tile.getObjects()){
				 if(Table.class.isInstance(object)){
					 table = (Table) object;
					 getCharacter().rotateTo(tile);
					 return;
				 }
			 }
		 }
		 if(table==null){
			 cancel();
		 }
	 }
	 
	 @Override
	 public void run() {
		 getCharacter().incrementHappiness(-0.04);
		 Game.getInstance().earnGold(1);
		 if(getCharacter().getHygiene()<=20 || getCharacter().getEnergy()<=20) {
			 finish();
		 }
		 if(getCharacter().getBladder()<=20) {
			 getCharacter().stopEverything();
			 getCharacter().pee();
			 ((Adult)getCharacter()).work();
		 }
		 if(getCharacter().getHunger()<=20) {
			 getCharacter().stopEverything();
			 getCharacter().eat();
			 ((Adult)getCharacter()).work();
		 }
	 }

}
