package model.items;

import model.GameObject;
import model.Tile;

public class Wall extends GameObject implements Obstacle{

    public Wall(Tile pos) {
        super(pos);
    }

}
