package model.characters;

import model.characters.states.FetchingItem;
import model.characters.states.ReadingMagicBook;
import model.characters.states.StoringItem;
import model.items.MagicBook;
import model.map.Bookshelf;

public class ChildWizard extends Child implements Wizard {

	double magicPower = 50;

	public ChildWizard(String gender) {
		super(gender);
		ID = "Child Wizard ("+gender+")";
	}
	@Override
	public double getMagicPower() {
		return magicPower;
	}

	@Override
	public void incrementMagicPower(double i) {
		magicPower = Math.max(Math.min(magicPower+i,100),0);
	}


	public void readMagicBook(){
		getStateQueue().add(new FetchingItem(this,9, MagicBook.class));
		getStateQueue().add(new ReadingMagicBook(this,9));
		getStateQueue().add(new StoringItem(this,9,MagicBook.class, Bookshelf.class));
	}

}
