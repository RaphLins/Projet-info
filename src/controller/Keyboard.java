package controller;

import model.Game;
import model.characters.Character;
import model.items.Item;
import model.map.GameObject;
import view.MapView;
import view.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            case KeyEvent.VK_ESCAPE:
                window.showMainMenu();
                break;
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
            case KeyEvent.VK_M: //to move an object
                GameObject selected = game.getSelectedObject();
                boolean mapEditable = game.isMapEditable();
                if(selected !=null){
                    if(!(selected instanceof Character)){
                        if((selected.getPos().getLocation() == Game.getInstance().getFamilyHouse() || mapEditable)){
                            selected.removeFromMap();
                            game.setDraggedObject(selected);
                        }
                    }
                    else {
                        Game.getInstance().getWindow().message("Can't move that.");
                    }
                }
                else {
                    Game.getInstance().getWindow().message("Select an object to move it.");
                }
                break;
            case KeyEvent.VK_R://to remove an object
                GameObject selected2 = game.getSelectedObject();
                boolean mapEditable2 = game.isMapEditable();
                if(selected2 instanceof Item){
                    if(selected2.getPos().getLocation() == Game.getInstance().getFamilyHouse() || mapEditable2){
                        game.earnGold((int)(((Item)selected2).getPrice()*0.8));
                        selected2.removeFromMap();
                    }
                }
                else {
                    Game.getInstance().getWindow().message("Can't sell that.");
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
