package model;

import model.characters.AdultWizard;
import model.map.Map;
import model.map.Tile;
import view.Window;
import model.characters.Character;

import java.awt.event.WindowEvent;


public class Game {
    private static Game instance = null;
    private static boolean started = false;
    private GameObject selectedObject = null;

    private Window window;
    private Map map;

    private Game() {
        map = new Map("shared/res/map.csv");
        new AdultWizard(map.getTileAt(10,10));
        new AdultWizard(map.getTileAt(20,10));
        new AdultWizard(map.getTileAt(30,10));
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