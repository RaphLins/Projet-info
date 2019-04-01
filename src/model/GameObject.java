package model;

import model.map.Tile;

public abstract class GameObject {
    private int color;
    private Tile pos;
    private int x;
    private int y;

    public String ID;

    public GameObject(Tile pos) {
        this.pos = pos;
        pos.addObject(this);
        ID = "";
    }

    public void move(Tile target){
        pos.removeObject(this);
        pos = target;
        target.addObject(this);
    }

    public int getPosX() {
        return pos.getX();
    }

    public int getPosY() {
        return pos.getY();
    }

    public boolean isAtPosition(int x, int y) {
        return getPosX() == x && getPosY() == y;
    }

    public Tile getPos(){
        return pos;
    }
}
