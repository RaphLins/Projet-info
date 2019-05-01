package model;

import model.characters.AdultWizard;
import model.items.CarriableItem;
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
    GameObject draggedObject = null;
    private ArrayList<Character> family = new ArrayList<>();
    private int familyGold = 1000;

    private Window window;
    private Map map;
    private Thread gameTime;
    ObjectHolder selectedOH;
    private boolean itemToAdd = false;

    private Game() {
        map = new Map("shared/res/map.csv");
        //Time.getInstance().run();
        family.add(new AdultWizard());
        family.get(0).setPos(map.getTileAt(10,10),map);
        //family.add(new AdultWizard());
        //family.get(1).setPos(map.getTileAt(10,20),map);
        //family.add(new AdultWizard());
        //family.get(2).setPos(map.getTileAt(10,30),map);
        family.get(0).carryItem(new Wand());
        //family.get(1).carryItem(new Wand());
        //family.get(2).carryItem(new Wand());
        family.get(0).carryItem(new Wand());
        family.get(0).carryItem(new Wand());
        family.get(0).carryItem(new Wand());
        family.get(0).carryItem(new Wand());

        gameTime = new Thread(Time.getInstance());
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
        selectedObject = object;
        if(object instanceof ObjectHolder) {
        	selectedOH = (ObjectHolder)object;
        }
        if(object instanceof Character){
            //((Character)object).stopEverything();
        }
        if(itemToAdd && object instanceof CarriableItem) {
        	selectedOH.addItem(object);
        	itemToAdd =false;
        }
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
    


    public static Game getInstance(){
        if(instance == null && !started){
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