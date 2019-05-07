package view;

import controller.Mouse;
import model.Game;
import model.GameObject;
import model.map.Map;
import model.characters.Directable;
import model.map.Tile;
import model.map.Wall;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

public class MapView extends JPanel {
    public static final int TILE_WIDTH =  20;
    public static final int TILE_HEIGHT =  20;
    private float zoom = 2;
    private static int paintCount = 0;

    private int viewPosX = 0;
    private int viewPosY = 0;
    private int mouseX = 0;
    private int mouseY = 0;
    private Mouse mouseController = null;
    TextureHashMap textures = new TextureHashMap();

    public MapView() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setPreferredSize(new Dimension(1660, 1020));
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = (int)(e.getX()/(TILE_WIDTH*zoom)+viewPosX);
                int y = (int)(e.getY()/(TILE_HEIGHT*zoom)+viewPosY);
                if(mouseX!=x | mouseY!=y){
                    mouseX=x;
                    mouseY=y;
                    repaint();
                }
            }
        });
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
            for(int i = 0; i < getWindowWidth()+1; i++){
                if(i<Map.WIDTH && j<Map.HEIGHT){
                    drawTile(getTileAtWindowPos(i,j),i,j,g);
                }
                else{
                    g.drawImage(textures.get("White"),i*TILE_WIDTH,(j-1)*TILE_HEIGHT,TILE_WIDTH,2*TILE_HEIGHT,null);
                }
            }
        }

        if(selected!=null && selected.getPos() != null){
            int x = (int)((selected.getExactX()-viewPosX)*TILE_WIDTH*zoom);
            int y = (int)((selected.getExactY()-viewPosY)*TILE_HEIGHT*zoom);
            g.setColor(new Color(255,204,153,120));
            g.fillOval(x,y,TILE_WIDTH*(int)zoom,TILE_HEIGHT*(int)zoom);
        }
        //System.out.println("painted "+paintCount);
        //paintCount++;
    }

    private void drawTile(Tile tile, int i,int j, Graphics g){
        drawImage(textures.get(tile.ID),i,j,g);//draw background
        ArrayList<GameObject> objects = (ArrayList<GameObject>)tile.getObjects().clone();//clone to avoid concurrent modification exception
        for(GameObject object: objects){//draw items
            String id = object.ID;
            if (object instanceof Directable) {
                id += ((Directable) object).getDirection();
            }
            if(id=="Wall" && Math.hypot(mouseX-viewPosX-i,mouseY-viewPosY-j)<=1.5){//hide walls around mouse
                id="Cut Wall";
            }
            BufferedImage image = textures.get(id);
            if(image!=null){
                drawImage(image, object.getExactX() - viewPosX,object.getExactY() - viewPosY,g);
            }
        }

        GameObject draggedObject = Game.getInstance().getDraggedObject();
        if(i==mouseX-viewPosX && j==mouseY-viewPosY && draggedObject!=null){//draw dragged object
            ((Graphics2D)g).setComposite(AlphaComposite.SrcOver.derive(0.6f));//draw transparent
            drawImage(textures.get(draggedObject.ID),i,j,g);
            ((Graphics2D)g).setComposite(AlphaComposite.SrcOver);//end transparent
        }
    }

    private void drawImage(BufferedImage image, float x, float y, Graphics g){
        int x2 = (int) ((x) * TILE_WIDTH * zoom);
        int y2 = (int) (((y + 1) * TILE_HEIGHT - image.getHeight()) * zoom);
        g.drawImage(image, x2, y2, (int) (image.getWidth() * zoom), (int) (image.getHeight() * zoom), null);
    }

    public Tile getTileAtWindowPos(int x, int y){
        return Game.getInstance().getMap().getTileAt(x+viewPosX,y+viewPosY);
    }

    public void redraw() {
        this.repaint();
    }

    public void addMouse(Mouse m) {
        this.mouseController = m;
    }

    public void updateTile(int x, int y) {
        if(viewPosX <x && x < viewPosX+getWindowWidth() && viewPosY <y && y < viewPosY+getWindowHeight()){
            this.repaint();
        }
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
