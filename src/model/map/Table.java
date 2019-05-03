package model.map;

import model.Game;
import model.characters.Character;
import model.items.HoldableItem;
import model.items.Item;

import java.util.ArrayList;

public class Table extends Item implements Obstacle {
    public Table() {
        super();
        ID = "Table";
        height=2;
        width=3;
        price = 50;
        accessDirections = new int[]{SOUTH,NORTH};
    }
}
