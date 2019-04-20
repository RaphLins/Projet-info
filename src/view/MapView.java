package view;

import controller.Mouse;
import model.Game;
import model.GameObject;
import model.map.Map;
import model.characters.Directable;
import model.map.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

public class MapView extends JPanel {
    public static final int TILE_WIDTH =  20;
    public static final int TILE_HEIGHT =  20;
    private float zoom = 2;
    private static int tilesDrawnNbr = 0;

    private Map map;

    private int viewPosX = 0;
    private int viewPosY = 0;
    private Mouse mouseController = null;
    TextureHashMap textures = new TextureHashMap();;

    public MapView(Map map) {
        this.map = map;

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(1660, 1020));
        addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {
                int x = (int)(e.getX()/(TILE_WIDTH*zoom)+viewPosX);
                int y = (int)(e.getY()/(TILE_HEIGHT*zoom)+viewPosY);
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
        this.addMouseWheelListener(e -> {
            zoom=e.getWheelRotation()<0?2:1;
            centerView(e.getX()/TILE_WIDTH,e.getY()/TILE_HEIGHT);
            repaint();
        });
    }

    public void paint(Graphics g) {
        GameObject selected = Game.getInstance().getSelectedObject();
        for(int j = 0; j < getWindowHeight()+2; j++){
            ArrayList<GameObject> rowObjects = new ArrayList<>();
            for(int i = 0; i < getWindowWidth()+1; i++){
                if(i<Map.WIDTH && j<Map.HEIGHT){
                    Tile tile = getTileAtWindowPos(i,j);
                    rowObjects.addAll(tile.getObjects());
                    BufferedImage image = textures.get(tile.ID);
                    int x = (int)((i*TILE_WIDTH)*zoom);
                    int y = (int)(((j+1)*TILE_HEIGHT-image.getHeight())*zoom);
                    g.drawImage(image,x,y,(int)(image.getWidth()*zoom), (int)(image.getHeight()*zoom),null);
                }
                else{
                    g.drawImage(textures.get("White"),i*TILE_WIDTH,(j-1)*TILE_HEIGHT,TILE_WIDTH,2*TILE_HEIGHT,null);
                }
            }

            for(GameObject object: rowObjects){
                String id = object.ID;
                if(object instanceof Directable){
                    id +=((Directable)object).getDirection();
                }
                BufferedImage image = textures.get(id);
                int x = (int)((object.getExactX()-viewPosX)*TILE_WIDTH*zoom);
                int y = (int)(((object.getExactY()-viewPosY+1)*TILE_HEIGHT-image.getHeight())*zoom);
                g.drawImage(image,x,y,(int)(image.getWidth()*zoom), (int)(image.getHeight()*zoom),null);

            }

        }
        if(selected!=null){
            int x = (int)((selected.getExactX()-viewPosX)*TILE_WIDTH*zoom);
            int y = (int)((selected.getExactY()-viewPosY)*TILE_HEIGHT*zoom);
            g.setColor(new Color(255,204,153,120));
            g.fillOval(x,y,TILE_WIDTH*(int)zoom,TILE_HEIGHT*(int)zoom);
        }
        //System.out.println("painted map");
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
        //System.out.println("painted "+x+" "+y);
    }

    public void moveView(int dx, int dy){
        setViewPos(viewPosX+dx, viewPosY+dy);
    }

    private void setViewPos(int x, int y){
        viewPosX = Math.max(Math.min(x,Map.WIDTH-getWindowWidth()-1),0);
        viewPosY = Math.max(Math.min(y,Map.HEIGHT-getWindowHeight()-1),0);
        repaint();
    }

    private int getWindowWidth(){
        return (int)(getBounds().width/(TILE_WIDTH*zoom));
    }

    private int getWindowHeight(){
        return (int)(getBounds().height/(TILE_HEIGHT*zoom));
    }


    public void centerView(int x, int y){
        setViewPos(x-getWindowWidth()/2,y-getWindowHeight()/2);
    }

}
