package model;

import model.Tile;

public abstract class GameObject {
    private int color;
    private Tile pos;

    public GameObject(Tile pos) {
        this.pos = pos;
    }


    public void move(Tile tile){
        pos.removeObject(this);
        pos = tile;
        tile.addObject(this);
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
}
