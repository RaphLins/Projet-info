package model;

import model.items.HoldableItem;

import java.util.ArrayList;

public interface ObjectHolder {
    ArrayList<HoldableItem> getInventory();
}
