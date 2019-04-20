package model;

import model.characters.AdultWizard;
import model.items.Wand;
import model.map.Map;
import model.map.Tile;
import view.Window;
import model.characters.Character;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import model.Time;


public class Game {
    private static Game instance = null;
    private static boolean started = false;
    private GameObject selectedObject = null;
    private ArrayList<Character> family = new ArrayList<>();

    private Window window;
    private Map map;

    private Game() {
        map = new Map("shared/res/map.csv");
        family.add(new AdultWizard(map.getTileAt(35,20)));
        family.add(new AdultWizard(map.getTileAt(41,32)));
        family.add(new AdultWizard(map.getTileAt(45,17)));

        family.get(0).carryItem(new Wand());
        family.get(1).carryItem(new Wand());
        family.get(2).carryItem(new Wand());
        family.get(0).carryItem(new Wand());
        family.get(0).carryItem(new Wand());
        family.get(0).carryItem(new Wand());
        family.get(0).carryItem(new Wand());

        Thread gameTime = new Thread(Time.getInstance());
        gameTime.start();
    }

    public void setWindow(Window window){
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }

    public GameObject getSelectedObject(){
        return selectedObject;
    }

    public void selectObject(GameObject object){
        selectedObject = object;
    }

    public void stop() {
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    public Map getMap(){
        return map;
    }
    
    public ArrayList<Tile> getTilesAround(Character c) {
    	ArrayList<Tile> tilesAround = new ArrayList<Tile>();
    	for(int i=0;i<20;i++) {
    		for(int j =0; j<20 ; j++) {
    			tilesAround.add(map.getTileAt(c.getPos().getX()+i, c.getPos().getY()+j));
    			tilesAround.add(map.getTileAt(c.getPos().getX()-i, c.getPos().getY()+j));
    			tilesAround.add(map.getTileAt(c.getPos().getX()+i, c.getPos().getY()-j));
    			tilesAround.add(map.getTileAt(c.getPos().getX()-i, c.getPos().getY()-j));    			
    		}
    	}
    	return tilesAround;
    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
            started = true;
        }
        return instance;
    }

    public static boolean isStarted(){
        return started;
    }

    public void updateTile(int x, int y) {
        window.updateTile(x,y);
    }

}