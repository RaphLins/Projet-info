package model.map;

import model.items.Item;

public class Table extends Item implements Obstacle {
    public Table() {
        super();
        ID = "Table";
        height=2;
        width=3;
        price = 50;
    }
}
