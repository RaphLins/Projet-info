package model.map;
public class Wall extends Tile {
    public Wall(Tile tile) {
        super(tile);
        ID = "wall";
    }
    @Override
    public boolean isWalkable() {
        return false;
    }
}