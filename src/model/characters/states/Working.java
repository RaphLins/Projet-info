package model.characters.states;

import model.Game;
import model.characters.Adult;
import model.characters.Character;
import model.map.GameObject;
import model.map.Tile;
import model.map.mapObjects.Stool;
import model.map.mapObjects.Table;
import model.places.WorkablePlace;

public class Working extends UsingItem{

	private WorkablePlace workablePlace;

	public Working(Character character, int groupID, WorkablePlace workablePlace) {
		super(character, groupID, Stool.class);
		this.workablePlace = workablePlace;
	}
	
	 @Override
	 public void init() {//rotates to table after sitting
		 super.init();
		 getCharacter().setLocation(workablePlace);
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
		 getCharacter().incrementHappiness(-0.01);
		 Game.getInstance().earnGold(1);
		 if(getCharacter().getHygiene()<=70 || getCharacter().getEnergy()<=18 || Game.getInstance().getTime().getHours()>=19) {//stop working
			 finish();
			 getCharacter().pee();
		 }
		 else if(getCharacter().getBladder()<=20) {//pause working
			 super.cancel();
			 ((Adult)getCharacter()).work();
		 }
		 else if(getCharacter().getHunger()<=35) {//pause working
			 super.cancel();
			 ((Adult)getCharacter()).work();
		 }
	 }

	@Override
	public void finish() {
		getCharacter().setLocation(getCharacter().getHouse());
		super.finish();
	}

	@Override
	public void cancel() {
		getCharacter().setLocation(getCharacter().getHouse());
		super.cancel();
	}

}

