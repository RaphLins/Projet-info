package src.view;

import java.awt.Graphics;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Map extends JPanel {
    private static int MAP_WIDTH;
    private static int MAP_HEIGHT;
    private static final int WINDOW_WIDTH = 80;
    private static final int WINDOW_HEIGHT = 50;
    public static final int WINDOW_PIXEL_WIDTH = Tile.WIDTH*WINDOW_WIDTH;
    public static final int WINDOW_PIXEL_HEIGHT = Tile.HEIGHT*WINDOW_HEIGHT;

    private int viewPosX = 0;
    private int viewPosY = 0;

    private Tile[][] grid;

    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();

        MAP_HEIGHT = 1000;
        MAP_WIDTH = 1000;
        grid = new Tile[MAP_HEIGHT][MAP_WIDTH];
        for(int i = 0; i < MAP_HEIGHT; i++){
            for(int j = 0; j< MAP_WIDTH; j++){
                grid[i][j] = new Tile(i,j);
            }
        }
    }

    public Map(String fileName) {
        this.setFocusable(true);
        this.requestFocusInWindow();

        List<String> tempList = new ArrayList<>();
        try {
            tempList = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MAP_HEIGHT = tempList.size();
        MAP_WIDTH = tempList.get(0).length();
        grid = new Tile[MAP_HEIGHT][MAP_WIDTH];

        ObjectFactory objectFactory = new ObjectFactory();
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                grid[i][j] = new Tile(i, j);
                if(j<tempList.get(i).length()){
                    char objectChar = tempList.get(i).charAt(j);
                    grid[i][j].addObject(objectFactory.getInstance(objectChar));
                }
            }
        }
    }

    public void paint(Graphics g) {
        for(int i = viewPosY; i < Math.min(WINDOW_HEIGHT, MAP_HEIGHT); i++){
            for(int j = viewPosX; j < Math.min(WINDOW_WIDTH, MAP_WIDTH); j++){
                grid[i][j].paint(g);
            }
        }
    }

    public void redraw() {
        this.repaint();
    }

}
