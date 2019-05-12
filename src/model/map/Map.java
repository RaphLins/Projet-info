package model.map;

import model.Game;
import model.characters.Character;
import model.items.HoldableItem;
import model.places.House;
import model.places.MinistryOfMagic;
import model.places.Place;
import model.places.School;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map implements Serializable{
    private int width;
    private int height;
    private ArrayList<Place> places = new ArrayList<>();

    private Tile[][] grid;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
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
        height = tempList.size();
        width = tempList.get(0).split(",",-1).length;
        grid = new Tile[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j< height; j++){
                grid[i][j] = new Tile(i,j);
            }
        }//create array
        ObjectFactory tileFactory = new ObjectFactory();
        for (int j = 0; j < height; j++) {
            String[] row = tempList.get(j).split(",",-1);
            for (int i = 0; i < width; i++) {
                ArrayList<String> types = new ArrayList();
                if(i<row.length){
                    types = new ArrayList(Arrays.asList(row[i].split(" ")));
                    for(String type: types){
                        GameObject object = tileFactory.getInstance(type);
                        if(object!=null){
                            object.setPos(grid[i][j],this);
                        }
                        if(type.equals(".")){
                            grid[i][j].setID("Floor");
                        }
                        else if(type.equals("*")){
                            grid[i][j].setID("Road");
                        }
                        else {
                        	if(type!=null) {
                        		String split[] = type.split("-");
                            	if(split.length>=2){
                            		String type2 = split[0];
                            		String dimensions[] = split[1].split("x");
                            		int width = Integer.parseInt(dimensions[0]);
                            		int height = Integer.parseInt(dimensions[1]);
                            		if(type2.equals("House")) {
                            			House house = new House(grid[i][j],height,width,this);
                            			if(split.length>=3 && split[2].equals("fam")) {
                            				Game.getInstance().setFamilyHouse(house);
                            				for(Character c : Game.getInstance().getFamily()) {
                            					c.setHouse(house);
                            					c.setLocation(house);
                            				}
                            			}
                            		}
                            		else if (type2.equals("School")) {
                            			School school = new School(grid[i][j],height,width,this);
                            			places.add(school);
                            		}
                            		else if (type2.equals("MinistryOfMagic")){
                            			MinistryOfMagic ministryOfMagic = new MinistryOfMagic(grid[i][j],height,width,this);
                                        places.add(ministryOfMagic);
                            		}
                            	}
                            	
                        	}
                        }
                    }
                }
            }//add objects
        }
        for(Character c : tileFactory.getCharactersToAssign()) {
        	c.setHouse((House)(c.getPos().getLocation()));
            c.setLocation((House)(c.getPos().getLocation()));
        }
    }

    public Tile getTileAt(int x, int y){
        return grid[Math.max(Math.min(x, width -1),0)][Math.max(Math.min(y, height -1),0)];
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
        for(int i=-distanceMax;i<=distanceMax;i++) {
            for(int j =-distanceMax; j<=distanceMax; j++) {
                tilesAround.add(getTileAt(x+i,y+j));
            }
        }
        return tilesAround;
    }

    private ArrayList<GameObject> findObjectsByType(Class type, ArrayList<Tile> tiles){
        ArrayList<GameObject> res = new ArrayList<>();
        for(Tile tile : tiles) {
            ArrayList<GameObject> objects = tile.getObjects();
            for(GameObject o : objects) {
                if((!(o instanceof Character) && o instanceof ObjectHolder)){
                    ArrayList<HoldableItem> objects2 = ((ObjectHolder)o).getInventory();
                    for(GameObject o2 : objects2) {
                        if (type.isInstance(o2)) {
                            res.add(o2);
                        }
                    }
                }
                if (type.isInstance(o)) {
                    if(o instanceof Obstacle){
                        res.add(o);
                    }
                    else {
                        if(tile.isWalkable()){
                            res.add(o);
                        }
                    }
                }
            }
        }
        return res;
    }

    public ArrayList<GameObject> getNearbyObjects(Tile position, Class type, int distanceMax){
        return findObjectsByType(type, getNearbyTiles(position,distanceMax));
    }
    public Tile getClosestTile(Tile position, Class type, Place location) {
        return getClosestTile(position, getAllAccessTiles(findObjectsByType(type,location.getArea())));
    }

    private ArrayList<Tile> getAllAccessTiles(ArrayList<GameObject> objects){
        ArrayList<Tile> accessTiles =  new ArrayList<>();
        for(GameObject object:objects){
            accessTiles.addAll(object.getAccessTiles());
        }
        return accessTiles;
    }

    public Tile getClosestTile(Tile position, Class type, int distanceMax) {
        ArrayList<Tile> possibleTargets = getAllAccessTiles(getNearbyObjects(position,type,distanceMax));
        if(possibleTargets.isEmpty()){
            return null;
        }
        else{
            return getClosestTile(position, possibleTargets);
        }
    }

    public Tile getClosestTile(Tile position, ArrayList<Tile> possibleTargets) { //utilise les coordonnees des cases contenant l'objet qu'on cherche pour determiner celle qui est la plus proche a vol d'oiseau
        if(possibleTargets.isEmpty()){
            return null;
        }
        Tile res = possibleTargets.get(0);
        float currentDistance = res.distanceTo(position);
        for (Tile tile : possibleTargets) {
            float distance = tile.distanceTo(position);
            if(distance<currentDistance) {
                res = tile;
                currentDistance = distance;
            }
        }
        return res;
    }

    public GameObject getClosestObject(Tile position, ArrayList<GameObject> possibleTargets) { //utilise les coordonnees des cases contenant l'objet qu'on cherche pour determiner celle qui est la plus proche a vol d'oiseau
        if(possibleTargets.isEmpty()){
            return null;
        }
        GameObject res = possibleTargets.get(0);
        float currentDistance = res.getPos().distanceTo(position);
        for (GameObject obj : possibleTargets) {
            float distance = obj.getPos().distanceTo(position);
            if(distance<currentDistance) {
                res = obj;
                currentDistance = distance;
            }
        }
        return res;
    }

    public Place getClosestPlace(Tile tile, Class type) {
        ArrayList<Tile> possibleTargets = new ArrayList<>();
        for(Place place : places) {
            if(type.isInstance(place)){
                possibleTargets.add(place.getPos());
            }
        }
        return Game.getInstance().getMap().getClosestTile(tile, possibleTargets).getLocation();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
