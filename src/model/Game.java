package model;

import model.characters.AdultWizard;
import model.characters.ChildWizard;
import model.items.HoldableItem;
import model.items.Wand;
import model.map.Map;
import view.Window;
import model.characters.Character;

import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;


public class Game implements Serializable {
    private static Game instance = null;
    private static boolean started = false;
    private GameObject selectedObject = null;
    GameObject draggedObject = null;
    private ArrayList<Character> family = new ArrayList<>();
    private int familyGold = 1000;

    transient private Window window;
    private Map map;
    private boolean itemToAdd = false;

    Time time;
    transient Thread gameTime;

    private Game() {
        map = new Map("shared/res/map.csv");
        //Time.getInstance().run();
        family.add(new AdultWizard("M"));
        family.get(0).setPos(map.getTileAt(10,10),map);
        family.add(new AdultWizard("F"));
        family.get(1).setPos(map.getTileAt(19,10),map);
        family.add(new ChildWizard("M"));
        family.get(2).setPos(map.getTileAt(15,30),map);
        family.add(new ChildWizard("F"));
        family.get(3).setPos(map.getTileAt(17,20),map);
        family.get(0).carryItem(new Wand());
        family.get(0).carryItem(new Wand());
        time = Time.getInstance();
        startTime();
    }


    public void stopTime(){
        if(gameTime!=null){
            gameTime.stop();
        }
    }
    public void startTime(){
        Time.setInstance(time);
        gameTime = new Thread(time);
        gameTime.start();
    }

    public void setWindow(Window window){
        this.window = window;
    }
    
    public void itemToAdd() {
    	itemToAdd = true;
    }

    public Window getWindow() {
        return window;
    }

    public GameObject getSelectedObject(){
        return selectedObject;
    }

    public void selectObject(GameObject object){
        if(itemToAdd) {
            if(selectedObject instanceof ObjectHolder && object instanceof HoldableItem){
                ((HoldableItem) object).storeIn((ObjectHolder)selectedObject);
            }
        }
        else {
            selectedObject = object;
        }
        itemToAdd = false;
        getWindow().updateStatus();
        getWindow().getMapView().repaint();
        getWindow().updateInventory();
        getWindow().updateActionView();
    }

    public GameObject getDraggedObject(){
        return draggedObject;
    }

    public void setDraggedObject(GameObject object){
        draggedObject = object;
        window.getMapView().repaint();
    }

    public void stop() {
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    public Map getMap(){
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public static Game getInstance(){
        if(instance == null && !started){
            instance = new Game();
            started = true;
        }
        return instance;
    }

    public static void setInstance(Game game){
        instance = game;
    }

    public static boolean isStarted(){
        return started;
    }

    public void updateTile(int x, int y) {
        window.updateTile(x,y);
    }

    public int getGold(){
        return familyGold;
    }

    public boolean spendGold(int val){
        if(familyGold-val>=0){
            familyGold-=val;
            getWindow().updateGold();
            return true;
        }
        else return false;
    }

    public void earnGold(int val){
        familyGold+=val;
        getWindow().updateGold();
    }

    public ArrayList<Character> getFamily() {
        return family;
    }

}