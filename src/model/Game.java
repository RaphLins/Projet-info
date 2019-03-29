package model;

import model.characters.AdultWizard;
import view.Window;
import model.characters.Character;

import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class Game {
    public static Game instance = null;
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private ArrayList<Character> characters = new ArrayList<Character>();
    private Character active_player = null;

    private Window window;
    private Map map;

    public Game(Window window) {
        this.window = window;
        instance = this;
        // Creating one Player at position (1,1)
        map = new Map();
        Character p = new AdultWizard(map.getTileAt(0,0));
        objects.add(p);
        characters.add(p);
        window.setPlayer(p);
        active_player = p;
        notifyView();
    }


    public void movePlayer(int x, int y) {
        Tile nextTile = map.getTileAt(active_player.getPosX() + x, active_player.getPosY() + y);

        active_player.rotate(x, y);
        if (nextTile.isWalkable()) {
            active_player.move(x, y);
        }
        notifyView();
    }
    private void notifyView() {
        window.update();
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
}