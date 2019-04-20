package model.map;

import model.GameObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map{
    public static int WIDTH;
    public static int HEIGHT;

    private Tile[][] grid;

    public Map(int width, int height) {
        grid = new Tile[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j< height; j++){
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
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j< HEIGHT; j++){
                grid[i][j] = new Tile(i,j);
            }
        }//create array

        ObjectFactory tileFactory = new ObjectFactory();
        for (int j = 0; j < HEIGHT; j++) {
            String[] row = tempList.get(j).split(",",-1);
            for (int i = 0; i < WIDTH; i++) {
                ArrayList<String> types = new ArrayList();
                if(i<row.length){
                    types = new ArrayList(Arrays.asList(row[i].split(" ")));
                    for(String type: types){
                        GameObject object = tileFactory.getInstance(type,i,j);
                        if(object!=null){
                            grid[i][j].addObject(object);
                            object.setPos(grid[i][j],this);
                        }
                        if(type.equals(".")){
                            grid[i][j].ID = "Floor";
                        }
                    }
                }

            }
        }//add objects
    }

    public Tile getTileAt(int x, int y){
        return grid[Math.max(Math.min(x,WIDTH-1),0)][Math.max(Math.min(y,HEIGHT-1),0)];
    }

    public Tile[][] getGrid(){
        return grid;
    }

    public Tile getTileNextTo(Tile tile, int direction){
        Tile res = null;
        switch (direction) {
            case 0 : res = getTileAt(tile.getX()+1,tile.getY()); break;
            case 1 : res = getTileAt(tile.getX(),tile.getY()-1); break;
            case 2 : res = getTileAt(tile.getX()-1,tile.getY()); break;
            case 3 : res = getTileAt(tile.getX(),tile.getY()+1); break;
        }
        return res;
    }
}
