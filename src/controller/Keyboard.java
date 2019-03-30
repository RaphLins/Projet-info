package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Game;
import view.MapView;

public class Keyboard implements KeyListener {
    private Game game;
    private MapView mapView;

    public Keyboard(Game game, MapView mapView) {
        this.game = game;
        this.mapView = mapView;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        switch (key) {
        case KeyEvent.VK_RIGHT:
            mapView.moveView(1, 0);
            break;
        case KeyEvent.VK_LEFT:
            mapView.moveView(-1, 0);
            break;
        case KeyEvent.VK_DOWN:
            mapView.moveView(0, 1);
            break;
        case KeyEvent.VK_UP:
            mapView.moveView(0, -1);
             break;
         case KeyEvent.VK_Q:
             game.stop();
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
