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
    private Character selectedCharacter = null;

    private Window window;
    private Map map;

    private Game() {
        map = new Map("shared/res/map.csv");
        Character p = new AdultWizard(map.getTileAt(10,10));
        selectedCharacter = p;
    }

    public void setWindow(Window window){
        this.window = window;
    }


    public void movePlayer(int x, int y) {
        Tile nextTile = map.getTileAt(selectedCharacter.getPosX() + x, selectedCharacter.getPosY() + y);

        selectedCharacter.rotateTo(nextTile);
        if (nextTile.isWalkable()) {
            selectedCharacter.move(x, y);
        }
    }
    public Character getPlayer(){
        return selectedCharacter;
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