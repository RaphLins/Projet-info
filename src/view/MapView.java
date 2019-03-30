package view;

import controller.Mouse;
import model.GameObject;
import model.Map;
import model.Tile;
import model.characters.AdultWizard;
import model.characters.Character;
import model.characters.Directable;
import model.items.HouseWindow;
import model.items.Obstacle;
import model.items.Wall;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
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
        this.setPreferredSize(new Dimension(1000, 1020));
        addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                int x = e.getX()/TILE_WIDTH+viewPosX;
                int y = e.getY()/TILE_HEIGHT+viewPosY;
                mouseController.mapEvent(x, y);
            }
            public void mouseClicked(MouseEvent arg0) {}
            public void mouseEntered(MouseEvent arg0) {}
            public void mouseExited(MouseEvent arg0) {}
            public void mouseReleased(MouseEvent arg0) {}
        });
        textures = new TextureHashMap();

    }

    public void paint(Graphics g) {
        for(int i = 0; i < getBounds().width/TILE_WIDTH; i++){
            for(int j = 0; j < getBounds().height/TILE_HEIGHT; j++){
                if(i<Map.WIDTH && j<Map.HEIGHT){
                    g.drawImage(textures.get("grass"),i*TILE_WIDTH,(j-1)*TILE_HEIGHT,null);
                    for(GameObject object: map.getTileAt(viewPosX+i,viewPosY+j).getObjects()){
                        BufferedImage img = null;
                        if(object instanceof Wall)img = textures.get("wall");
                        else if(object instanceof AdultWizard)img = textures.get("adult_wizard"+((Directable)object).getDirection());
                        else if(object instanceof HouseWindow)img = textures.get("house_window");
                        g.drawImage(img,i*TILE_WIDTH,(j-1)*TILE_HEIGHT,null);
                    }
                    tilesDrawnNbr++;
                    //System.out.println(tilesDrawnNbr);
                }
            }
        }
        System.out.println("paint");
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
