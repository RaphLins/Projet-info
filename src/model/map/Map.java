package model.map;

import model.Game;
import model.GameObject;
import model.characters.Character;

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

    public ArrayList<Tile> getNearbyTiles(Tile tile, int distanceMax) {
        ArrayList<Tile> tilesAround = new ArrayList<>();
        int x = tile.getX();
        int y = tile.getY();
        for(int i=0;i<distanceMax;i++) {
            for(int j =0; j<distanceMax ; j++) {
                tilesAround.add(getTileAt(x+i,x+j));
                tilesAround.add(getTileAt(x-i,x+j));
                tilesAround.add(getTileAt(x+i,x-j));
                tilesAround.add(getTileAt(x-i,x-j));
            }
        }
        return tilesAround;
    }

    public Tile getClosestTile(Tile position, String type, int distanceMax) {
        Map map = Game.getInstance().getMap();
        ArrayList<Tile> tilesAround = getNearbyTiles(position,distanceMax);
        ArrayList<Tile> possibleTargets =  new ArrayList<>();
        for(Tile tile : tilesAround) {
            ArrayList<GameObject> objects = tile.getObjects();
            for(GameObject o : objects) {
                if (o.ID == type) {
                    possibleTargets.add(o.getPos());
                }
            }
        }
        //on a cr�� une liste contenant les cases autour du personnage contenant l'objet n�cessaire pour l'action
        Tile target = getClosestTile(position, possibleTargets); //choisit celle qui est la plus proche � vol d'oiseau
        return target;
    }

    public Tile getClosestTile(Tile position, ArrayList<Tile> possibleTargets) { //utilise les coordonn�es des cases contenant l'objet qu'on cherche pour d�terminer celle qui est la plus proche � vol d'oiseau
        Tile res = possibleTargets.get(0);
        for (Tile tile : possibleTargets) {
            float currentDistance = (float) Math.pow(Math.pow(res.getX()-position.getX(),2) + Math.pow(res.getY()-position.getY(),2),0.5);
            float distance = (float) Math.pow(Math.pow(tile.getX()-position.getX(),2) + Math.pow(tile.getY()-position.getY(),2),0.5);
            if(distance<currentDistance) {
                res = tile;
            }
        }
        return res;
    }
}
