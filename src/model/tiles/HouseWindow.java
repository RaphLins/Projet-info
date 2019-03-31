package model.tiles;

public class HouseWindow extends Tile {

    public HouseWindow(Tile tile) {
        super(tile);
    }
    @Override
    public boolean isWalkable() {
        return false;
    }
}
