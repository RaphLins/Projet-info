package model;

import model.characters.AdultWizard;
import view.Window;
import model.characters.Character;

import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class Game {
    private static Game instance = null;
    private static boolean started = false;
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Character> characters = new ArrayList<Character>();
    private Character active_player = null;

    private Window window;
    private Map map;

    private Game() {
        map = new Map("map.csv");
        Character p = new AdultWizard(map.getTileAt(0,0));
        objects.add(p);
        characters.add(p);
        active_player = p;
    }

    public void setWindow(Window window){
        this.window = window;
    }


    public void movePlayer(int x, int y) {
        Tile nextTile = map.getTileAt(active_player.getPosX() + x, active_player.getPosY() + y);

        active_player.rotate(x, y);
        if (nextTile.isWalkable()) {
            active_player.move(x, y);
        }
    }

    public Character getPlayer(){
        return active_player;
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
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