package model.map;

public class HouseWindow extends Tile {

    public HouseWindow(Tile tile) {
        super(tile);
        ID = "House Window";
    }
    @Override
    public boolean isWalkable() {
        return false;
    }
}
