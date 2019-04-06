package model;

import model.map.Tile;

public abstract class GameObject {
    private Tile pos;
    private float xOffset = 0;
    private float yOffset = 0;

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
        move(Game.getInstance().getMap().getTileNextTo(pos,direction));
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
    public void setPos(Tile pos) {
        this.pos = pos;
    }

}
