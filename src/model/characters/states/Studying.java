package model.characters.states;

import model.Game;
import model.characters.Child;
import model.characters.Character;
import model.characters.Wizard;
import model.map.GameObject;
import model.map.Tile;
import model.map.mapObjects.Bench;
import model.places.School;

public class Studying extends UsingItem {

	private School school;

	public Studying(Character character, int groupID, School school) {
		super(character, groupID, Bench.class);
		this.school = school;
	}

	@Override
	public void init() {
		super.init();
		getCharacter().setLocation(school);
	}

	@Override
	 public void run() {
		 getCharacter().incrementHappiness(-0.03);
		 getCharacter().incrementEnergy(-0.03);
		 
		 if(getCharacter().getHygiene()<=20 || getCharacter().getEnergy()<=20) {
			 finish();
		 }
		 else if(getCharacter().getBladder()<=20) {
			 cancel();
			 ((Child)getCharacter()).goToSchool();
		 }
		 else if(getCharacter().getHunger()<=70) {
			 cancel();
			 ((Child)getCharacter()).goToSchool();	
		 }
		if(getCharacter() instanceof Wizard) {
			((Wizard)getCharacter()).incrementMagicPower(0.015);
		}
	 }

	@Override
	public void finish() {
		getCharacter().setLocation(getCharacter().getHouse());
		System.out.println(getCharacter().getLocation());
		super.finish();
	}
}
