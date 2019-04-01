package model.map;

public class HouseWindow extends Tile {

    public HouseWindow(Tile tile) {
        super(tile);
        ID = "house_window";
    }
    @Override
    public boolean isWalkable() {
        return false;
    }
}
