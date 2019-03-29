package model;

import view.ObjectFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Map{
    public static int WIDTH;
    public static int HEIGHT;

    private Tile[][] grid;

    public Map() {
        HEIGHT = 1000;
        WIDTH = 1000;
        grid = new Tile[HEIGHT][WIDTH];
        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j< WIDTH; j++){
                grid[i][j] = new Tile(i,j);
            }
        }
    }

    public Map(String fileName) {
        List<String> tempList = new ArrayList<>();
        try {
            tempList = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HEIGHT = tempList.size();
        WIDTH = tempList.get(0).length();
        grid = new Tile[HEIGHT][WIDTH];

        ObjectFactory objectFactory = new ObjectFactory();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                grid[i][j] = new Tile(i, j);
                if(j<tempList.get(i).length()){
                    char objectChar = tempList.get(i).charAt(j);
                    grid[i][j].addObject(objectFactory.getInstance(objectChar));
                }
            }
        }
    }

    public Tile getTileAt(int x, int y){
        return grid[x][y];
    }
}
