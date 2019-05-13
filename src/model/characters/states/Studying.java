package model.characters.states;

import model.Game;
import model.characters.Character;
import model.characters.Child;
import model.characters.Wizard;
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
		getCharacter().setLocation(school);
		super.init();
	}

	@Override
	 public void run() {
		 getCharacter().incrementHappiness(-0.01);
		 getCharacter().incrementHunger(-0.05);
		 if(getCharacter().getEnergy()<=18 || Game.getInstance().getTime().getHours()>=17) {
			 finish();
			 getCharacter().pee();
		 }
		 else if(getCharacter().getBladder()<=20) {
			 super.cancel();
			 ((Child)getCharacter()).goToSchool();
		 }
		 else if(getCharacter().getHunger()<=60) {
			 super.cancel();
			 ((Child)getCharacter()).goToSchool();
		 }
		if(getCharacter() instanceof Wizard) {
			((Wizard)getCharacter()).incrementMagicPower(0.015);
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
