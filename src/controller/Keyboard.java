package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import model.Game;
import model.GameObject;
import model.characters.Character;
import model.items.Item;
import view.MapView;
import view.Window;

public class Keyboard implements KeyListener {
    private Window window;
    private MapView mapView;

    public Keyboard(Window window) {
        this.window = window;
        this.mapView = window.getMapView();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        Game game = Game.getInstance();
        int key = event.getKeyCode();
        switch (key){
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
                mapView.moveView(2, 0);
                break;
            case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
                mapView.moveView(-2, 0);
                break;
            case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                mapView.moveView(0, 2);
                break;
            case KeyEvent.VK_W: case KeyEvent.VK_UP:
                mapView.moveView(0, -2);
                break;
            case KeyEvent.VK_Q:
                game.stop();
                break;
            case KeyEvent.VK_M:
                GameObject selected = game.getSelectedObject();
                if(selected !=null){
                    if(!(selected instanceof Character)){
                        selected.removeFromMap();
                        game.setDraggedObject(selected);
                    }
                    else {
                        System.out.println("Can't move that");
                    }
                }
                else {
                    System.out.println("Select an object to move it");
                }
                break;
            case KeyEvent.VK_R:
                GameObject selected2 = game.getSelectedObject();
                if(selected2 instanceof Item){
                    game.earnGold((int)(((Item)selected2).getPrice()*0.8));
                    selected2.removeFromMap();
                }
                else {
                    System.out.println("Can't sell that");
                }
                break;
            case KeyEvent.VK_1:
                try {
                    FileOutputStream fileOut = new FileOutputStream(new File("gameSave.txt"));
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                    objectOut.writeObject(Game.getInstance());
                    objectOut.close();
                    System.out.println("Game saved");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case KeyEvent.VK_2:
                try {
                    FileInputStream fileIn = new FileInputStream(new File("gameSave.txt"));
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    Game.getInstance().stopTime();
                    Game.setInstance((Game)objectIn.readObject());
                    Game.getInstance().startTime();
                    Game.getInstance().setWindow(window);
                    window.attachClock();
                    window.getMapView().repaint();
                    window.updateInventory();
                    window.updateStatus();
                    window.updateGold();
                    objectIn.close();
                    System.out.println("Game restored");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
