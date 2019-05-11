import static org.junit.Assert.*;

import model.Game;
import model.characters.AdultWizard;
import model.characters.Character;
import model.map.GameObject;
import model.map.Map;
import model.map.Tile;
import model.map.mapObjects.Bed;
import model.map.mapObjects.Toilet;
import org.junit.Test;
import view.Window;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class UnitTests {
    @Test()
    public void test1(){
        Character c = new AdultWizard("M");
        Map map = new Map(50,50);
        c.setPos(map.getTileAt(2,2),map);
        Bed bed1 = new Bed();
        Bed bed2 = new Bed();
        bed1.setPos(map.getTileAt(4,2),map);
        bed2.setPos(map.getTileAt(40,40),map);
        ArrayList<GameObject> closestObjects = map.getNearbyObjects(c.getPos(), Bed.class,4);
        assertTrue(closestObjects.contains(bed1) && !closestObjects.contains(bed2));
    }

    @Test()
    public void test2(){
        Character c = new AdultWizard("M");
        Map map = new Map(50,50);
        c.setPos(map.getTileAt(2,2),map);
        Bed bed1 = new Bed();
        Bed bed2 = new Bed();
        bed1.setPos(map.getTileAt(4,2),map);
        bed2.setPos(map.getTileAt(40,40),map);
        ArrayList<GameObject> closestObjects = map.getNearbyObjects(c.getPos(), Bed.class,1);
        assertTrue(closestObjects.isEmpty());
    }

    @Test()
    public void test3(){
        Character c1 = new AdultWizard("M");
        Character c2 = new AdultWizard("M");
        Map map = new Map(50,50);
        c1.setPos(map.getTileAt(2,2),map);
        c2.setPos(map.getTileAt(2,2),map);
        Toilet toilet = new Toilet();
        toilet.setPos(map.getTileAt(4,2),map);
        assertTrue(toilet.use(c1));
        assertFalse(toilet.use(c2));
    }


    @Test(expected=IOException.class)
    public void test4() throws IOException, ClassNotFoundException {
        Game.getInstance().setWindow(new Window(""));
        Game.getInstance().newGame();
        Game.getInstance().restoreStateFromFile("nofile.txt");
    }
}