package model.map;

import model.Game;
import model.characters.Directable;
import model.map.Map;
import model.map.Tile;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class GameObject implements Serializable {
    private Tile pos;
    private ArrayList<Tile> allTiles = new ArrayList<>(); //an object can be on several tiles.
    private float xOffset = 0;
    private float yOffset = 0;
    public static final int EAST = 0;
    public static final int NORTH = 1;
    public static final int WEST = 2;
    public static final int SOUTH = 3;

    private int width;
    private int height;
    private String ID;
    private int[] accessDirections = new int[]{EAST,WEST,SOUTH,NORTH};

    public GameObject() {
        ID = "";
        width = 1;
        height = 1;
    }

    public void setOffsetInDirection(float d, int direction){
        switch (direction) {
            case EAST : xOffset =d; break;
            case NORTH : yOffset =-d; break;
            case WEST : xOffset =-d; break;
            case SOUTH : yOffset =d; break;
        }
        Game.getInstance().updateTile(pos.getX(),pos.getY());
    }

    public void moveInDirection(int direction){
        setPos(Game.getInstance().getMap().getTileNextTo(pos,direction));
    }

    public int getPosX() {
        return pos.getX();
    }

    public float getExactX(){
        return pos.getX()+xOffset;
    }

    public float getExactY(){
        return pos.getY()+yOffset;
    }

    public int getPosY() {
        return pos.getY();
    }

    public void resetOffset(){
        xOffset = 0;
        yOffset = 0;
    }

    public boolean isAtPosition(int x, int y) {
        return getPosX() == x && getPosY() == y;
    }

    public Tile getPos(){
        return pos;
    }

    public void removeFromMap(){
        for(Tile tile:allTiles){
            tile.removeObject(this);
        }
        allTiles.clear();
        pos=null;
    }

    public void setPos(Tile target){
        setPos(target,Game.getInstance().getMap());
    }

    public void setPos(Tile target, Map map) {
        removeFromMap();
        pos = target;
        for(int i = 0;i<width;i++){
            for(int j = 0;j<height;j++){
                Tile tile = map.getTileAt(pos.getX()+i,pos.getY()-j);
                tile.addObject(this);
                allTiles.add(tile);
            }
        }
    }

    public ArrayList<Tile> getAccessTiles(){
        Map map = Game.getInstance().getMap();
        ArrayList<Tile> tiles = new ArrayList<>();
        for(Tile tile: allTiles){
            if(tile.isWalkable() && !tiles.contains(tile)){
                tiles.add(tile);
            }
            else{
                for(int i:accessDirections){
                    Tile target2 = map.getTileNextTo(tile,i);
                    if(target2.isWalkable() && !tiles.contains(target2)) {
                        tiles.add(target2);
                    } //si le personnage ne peut pas aller sur la case, il va chercher a aller sur celles a cote de l'objet
                }
            }
        }
        return tiles;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAccessDirections(int[] accessDirections) {
        this.accessDirections = accessDirections;
    }

    public int[] getAccessDirections() {
        return accessDirections;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}
