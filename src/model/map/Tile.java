package model.map;

import model.Game;
import model.GameObject;

import java.util.ArrayList;

public class Tile{
	
	private int x;
	private int y;
	private boolean isWalkable = true;
	public String ID;

	private ArrayList<GameObject> objects = new ArrayList<>();
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		ID = "Grass";
	}

	public Tile(Tile tile) {
		this(tile.getX(),tile.getY());
		objects = tile.getObjects();
		update();
	}

	public boolean isWalkable(){
		return isWalkable;
	}

	public void addObject(GameObject object){
		if(object!=null){
			objects.add(object);
			update();
		}
	}

	public void removeObject(GameObject object){
		objects.remove(object);
		update();
	}

	public GameObject getTopObject(){
		if(objects.size()==0)return null;
		else return objects.get(objects.size()-1);
	}

	public void update(){
		isWalkable = true;
		for(GameObject object : objects){
			if(object instanceof Obstacle){
				isWalkable = false;
			}
		}
		if(Game.isStarted())Game.getInstance().updateTile(x,y);
	}


	public ArrayList<GameObject> getObjects(){
		return objects;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	float distanceTo(Tile tile){
		return (float) Math.pow(Math.pow(tile.getX()-getX(),2) + Math.pow(tile.getY()-getY(),2),0.5);
	}
}
