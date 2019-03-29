package view;

import Controller.Mouse;
import model.Map;
import model.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MapView extends JPanel {
    private static final int WINDOW_WIDTH = 80;
    private static final int WINDOW_HEIGHT = 50;
    public static final int WINDOW_PIXEL_WIDTH = Tile.WIDTH*WINDOW_WIDTH;
    public static final int WINDOW_PIXEL_HEIGHT = Tile.HEIGHT*WINDOW_HEIGHT;

    private Map map;

    private int viewPosX = 0;
    private int viewPosY = 0;
    private Mouse mouseController = null;

    public MapView(Map map) {
        this.map = map;
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(WINDOW_PIXEL_WIDTH, WINDOW_PIXEL_HEIGHT));
        addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                int x = e.getX()/Tile.WIDTH;
                int y = e.getY()/Tile.HEIGHT;
                mouseController.mapEvent(x, y);
            }
            public void mouseClicked(MouseEvent arg0) {}
            public void mouseEntered(MouseEvent arg0) {}
            public void mouseExited(MouseEvent arg0) {}
            public void mouseReleased(MouseEvent arg0) {}
        });
    }

    public void paint(Graphics g) {
        for(int i = viewPosY; i < Math.min(WINDOW_HEIGHT, Map.HEIGHT); i++){
            for(int j = viewPosX; j < Math.min(WINDOW_WIDTH, Map.WIDTH); j++){
                map.getTileAt(i,j).paint(g);
            }
        }
    }

    public void redraw() {
        this.repaint();
    }

    public void addMouse(Mouse m) {
        this.mouseController = m;
    }

}
