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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MapView extends JPanel {
    public static final int TILE_WIDTH =  20;
    public static final int TILE_HEIGHT =  20;
    private float zoom = 1;
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
        this.setPreferredSize(new Dimension(1500, 1020));
        addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {
                int x = e.getX()/TILE_WIDTH+viewPosX;
                int y = e.getY()/TILE_HEIGHT+viewPosY;
                if(e.getButton() == MouseEvent.BUTTON1) {
                    centerView(e.getX(),e.getY());
                    System.out.println("test");
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
        this.addMouseWheelListener(e -> {
            zoom-=e.getWheelRotation()<0?-0.1:0.1;
            zoom-=e.getWheelRotation()<0?-0.1:0.1;
            centerView(e.getX(),e.getY());
            repaint();
        });
    }

    public void paint(Graphics g) {
        for(int i = 0; i < getBounds().width/TILE_WIDTH+1; i++){
            for(int j = 0; j < getBounds().height/TILE_HEIGHT+1; j++){
                if(i<Map.WIDTH && j<Map.HEIGHT){
                    Tile tile = getTileAtWindowPos(i,j);
                    drawTile(tile.ID,i,j,g);
                    for(GameObject object: tile.getObjects()){
                        String id = object.ID;
                        if(object instanceof Directable){
                            id +=((Directable)object).getDirection();
                        }
                        drawTile(id,i,j,g);
                    }
                    tilesDrawnNbr++;
                    //System.out.println(tilesDrawnNbr);
                }
                else{
                    g.drawImage(textures.get("white"),i*TILE_WIDTH,(j-1)*TILE_HEIGHT,TILE_WIDTH,2*TILE_HEIGHT,null);
                }
            }
        }
        System.out.println("paint all");
    }

    private void drawTile(String id, int i, int j,Graphics g){
        BufferedImage image = textures.get(id);
        int x = (int)((i*TILE_WIDTH)*zoom);
        int y = (int)(((j+1)*TILE_HEIGHT-image.getHeight())*zoom);
        g.drawImage(image,x,y,(int)(image.getWidth()*zoom), (int)(image.getHeight()*zoom),null);
    }

    public Tile getTileAtWindowPos(int x, int y){
        return map.getTileAt(x+viewPosX,y+viewPosY);
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
        viewPosX = Math.max(Math.min(viewPosX+dx,Map.WIDTH-getBounds().width/TILE_WIDTH-1),0);
        viewPosY = Math.max(Math.min(viewPosY+dy,Map.HEIGHT-getBounds().height/TILE_HEIGHT-1),0);
        repaint();
    }

    public void centerView(int x, int y){
        System.out.println((x-getBounds().width/2)+" "+(y-getBounds().height/2));
        moveView((x-getBounds().width/2)/TILE_WIDTH,(y-getBounds().height/2)/TILE_HEIGHT);
    }

}
