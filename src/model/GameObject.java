package model;

import model.map.Map;
import model.map.Tile;

import java.util.ArrayList;

public abstract class GameObject {
    private Tile pos;
    private ArrayList<Tile> allTiles = new ArrayList<>(); //an object can be on several tiles.
    private float xOffset = 0;
    private float yOffset = 0;

    public int width;
    public int height;
    public String ID; 

    public GameObject() {
        ID = "";
        width = 1;
        height = 1;
    }
    public GameObject(Tile pos, Map map) {
        ID = "";
        width = 1;
        height = 1;
    }
    public void offSetInDirection(float d, int direction){
        switch (direction) {
            case 0 : xOffset +=d; break;
            case 1 : yOffset -=d; break;
            case 2 : xOffset -=d; break;
            case 3 : yOffset +=d; break;
        }
        Game.getInstance().updateTile(pos.getX(),pos.getY());
    }

    public void moveInDirection(int direction){
        setPos(Game.getInstance().getMap().getTileNextTo(pos,direction));
    }

    public int getXOffset(){
        return (int) xOffset;
    }
    public int getYOffset(){
        return (int) yOffset;
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
        for(Tile tile:allTiles){
            tile.removeObject(this);
        }
        allTiles.clear();
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
                tiles.add(getPos());
            }
            else{
                for(int i=0 ; i<4; i++) {
                    Tile target2 = map.getTileNextTo(getPos(),i);
                    if(target2.isWalkable() && !tiles.contains(target2)) {
                        tiles.add(target2);
                    } //si le personnage ne peut pas aller sur la case, il va chercher � aller sur celles � c�t� de l'objet
                }
            }
        }
        return tiles;
    }
}
