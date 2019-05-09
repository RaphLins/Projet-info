package model.characters;

import model.Game;
import model.GameObject;
import model.characters.states.FetchingItem;
import model.characters.states.ReadingMagicBook;
import model.characters.states.StoringItem;
import model.items.MagicBook;
import model.map.Bookshelf;
import model.map.Tile;

public class AdultWizard extends Adult implements Wizard {
    private double magicPower = 100;
    public AdultWizard(String gender) {
        super(gender);
        ID = "Adult Wizard ("+gender+")";
    }

    @Override
    public double getMagicPower() {
        return (float)magicPower;
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
