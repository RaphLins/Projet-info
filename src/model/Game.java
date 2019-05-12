package model;

import model.time.Time;
import model.items.HoldableItem;
import model.map.GameObject;
import model.map.Map;
import model.map.ObjectHolder;
import model.places.House;
import view.Window;
import model.characters.Character;

import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Game {
    private static Game instance = null;
    private static boolean started = false;
    private GameObject selectedObject = null;
    private GameObject draggedObject = null;
    private ArrayList<Character> family = new ArrayList<>();
    private int familyGold = 1000;
    private House familyHouse;

    private Window window;
    private Map map;
    private boolean itemToAdd = false;

    private Time time;
    private transient Thread gameTime;

    private Game() {
        time = new Time();
    }

    public void newGame(){
        stopTime();
        time = new Time();
        itemToAdd = false;
        selectedObject = null;
        draggedObject = null;
        family.clear();
        map = new Map("shared/res/map.csv");
        window.updateFamily();
        started = true;
        window.attachClock();
        startTime();
    }


    public void stopTime(){
        if(gameTime!=null){
            time.pause();
        }
    }
    public void startTime(){
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

    public void setFamilyHouse(House familyHouse) {
    	this.familyHouse = familyHouse;
    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
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
    
    public House getFamilyHouse() {
    	return familyHouse;
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

    public Time getTime() {
        return time;
    }

    public ArrayList<Character> getFamily() {
        return family;
    }

    public void saveStateToFile(String filename){
        try {
            FileOutputStream fileOut = new FileOutputStream(new File(filename));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            ArrayList<Serializable> save = new ArrayList<>();
            save.add(map);
            save.add(time);
            save.add(family);
            save.add(familyGold);

            objectOut.writeObject(save);
            objectOut.close();
            System.out.println("Game saved");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void restoreStateFromFile(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(new File(filename));
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        ArrayList<Serializable> saveFile = (ArrayList<Serializable>)objectIn.readObject();
        objectIn.close();

        stopTime();

        Iterator<Serializable> save = saveFile.iterator();
        map = (Map) save.next();
        time = (Time) save.next();
        family = (ArrayList<Character>) save.next();
        familyGold = (int) save.next();

        selectedObject = null;
        draggedObject = null;
        startTime();
        window.attachClock();
        window.getMapView().repaint();
        window.updateInventory();
        window.updateStatus();
        window.updateGold();
        window.updateFamily();
    }

}