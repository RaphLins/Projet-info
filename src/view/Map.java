package src.view;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Map extends JPanel {
    private static final int MAP_WIDTH =  80;
    private static final int MAP_HEIGHT =  49;
    private static final int TILE_WIDTH =  20;
    private static final int TILE_HEIGHT =  20;
    public static final int WIDTH =  MAP_WIDTH*TILE_WIDTH;
    public static final int HEIGHT =  MAP_HEIGHT*TILE_HEIGHT;

    private Tile[][] tiles = new Tile[MAP_HEIGHT][MAP_WIDTH];

    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        for(int i = 0; i < MAP_HEIGHT; i++){
            for(int j = 0; j< MAP_WIDTH; j++){
                int y = i*TILE_WIDTH;
                int x = j*TILE_HEIGHT;
                tiles[i][j] = new Tile(x,y);
            }
        }
    }

    public void paint(Graphics g) {
        for(int i = 0; i < MAP_HEIGHT; i++){
            for(int j = 0; j< MAP_WIDTH; j++){
                int y = i*TILE_WIDTH;
                int x = j*TILE_HEIGHT;
                g.setColor(tiles[i][j].getColor());
                g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
            }
        }
    }

    public void redraw() {
        this.repaint();
    }

}
