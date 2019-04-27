package model;

import java.util.ArrayList;

public interface ObjectHolder {
    ArrayList<GameObject> getInventory();
    void removeItem(GameObject object);
}
