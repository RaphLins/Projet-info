package view;

import controller.Mouse;
import model.GameObject;
import model.map.Map;
import model.characters.AdultWizard;
import model.characters.Directable;
import model.map.Floor;
import model.map.HouseWindow;
import model.map.Tile;
import model.map.Wall;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MapView extends JPanel {
    public static final int TILE_WIDTH =  20;
    public static final int TILE_HEIGHT =  20;
    private static int tilesDrawnNbr = 0;

    private Map map;

    private int viewPosX = 0;
    private int viewPosY = 0;
    private Mouse mouseController = null;
    TextureHashMap textures;

    public MapView(Map map) {
        this.map = map;

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(1750, 1020));
        addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {
                int x = e.getX()/TILE_WIDTH+viewPosX;
                int y = e.getY()/TILE_HEIGHT+viewPosY;
                if(e.getButton() == MouseEvent.BUTTON1) {
                    mouseController.mapEventLeftClick(x, y);
                }
                else if(e.getButton() == MouseEvent.BUTTON3) {
                    mouseController.mapEventRightClick(x, y);
                }
            }
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
        });
        textures = new TextureHashMap();

    }

    public void paint(Graphics g) {
        for(int i = 0; i < getBounds().width/TILE_WIDTH+1; i++){
            for(int j = 0; j < getBounds().height/TILE_HEIGHT+1; j++){
                if(i<Map.WIDTH && j<Map.HEIGHT){
                    Tile tile = map.getTileAt(i+viewPosX,j+viewPosY);
                    if(tile instanceof Wall)drawImage("wall",i,j,g);
                    else if(tile instanceof HouseWindow)drawImage("house_window",i,j,g);
                    else if(tile instanceof Floor)drawImage("floor",i,j,g);
                    else drawImage("grass",i,j,g);
                    for(GameObject object: tile.getObjects()){
                        if(object instanceof AdultWizard)drawImage("adult_wizard"+((Directable)object).getDirection(),i,j,g);
                    }
                    tilesDrawnNbr++;
                    //System.out.println(tilesDrawnNbr);
                }
            }
        }
        System.out.println("paint all");
    }

    private void drawImage(String id, int x, int y,Graphics g){
        g.drawImage(textures.get(id),x*TILE_WIDTH,(y-1)*TILE_HEIGHT,null);
    }

    public void redraw() {
        this.repaint();
    }

    public void addMouse(Mouse m) {
        this.mouseController = m;
    }

    public void updateTile(int x, int y) {
        this.repaint();
        System.out.println("painted "+x+" "+y);
    }

    public void moveView(int dx, int dy){
        viewPosX = Math.max(Math.min(viewPosX+dx,Map.WIDTH-getBounds().width/TILE_WIDTH),0);
        viewPosY = Math.max(Math.min(viewPosY+dy,Map.HEIGHT-getBounds().height/TILE_HEIGHT),0);
        System.out.println(viewPosX+" "+viewPosY);
        repaint();
    }
}
