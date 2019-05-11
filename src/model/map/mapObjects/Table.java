package model.map.mapObjects;

import model.items.Item;
import model.map.Obstacle;

public class Table extends Item implements Obstacle {
    public Table() {
        super();
        setID("Table");
        setHeight(2);
        setWidth(3);
        setPrice(50);
        setAccessDirections(new int[]{SOUTH,NORTH});
    }
}
