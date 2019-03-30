package model;

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
        HEIGHT = 50;
        WIDTH = 50;
        grid = new Tile[WIDTH][HEIGHT];
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j< HEIGHT; j++){
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
        WIDTH = tempList.get(0).split(",",-1).length;
        grid = new Tile[WIDTH][HEIGHT];
        System.out.println(WIDTH);

        ObjectFactory objectFactory = new ObjectFactory();
        for (int j = 0; j < HEIGHT; j++) {
            String[] row = tempList.get(j).split(",",-1);
            for (int i = 0; i < WIDTH; i++) {
                grid[i][j] = new Tile(i,j);
                if(i<row.length){
                    String type = row[i];
                    grid[i][j].addObject(objectFactory.getInstance(type,grid[i][j]));
                }
            }
        }
    }

    public Tile getTileAt(int x, int y){
        return grid[Math.max(Math.min(x,WIDTH-1),0)][Math.max(Math.min(y,HEIGHT-1),0)];
    }

    public Tile[][] getGrid(){
        return grid;
    }
}