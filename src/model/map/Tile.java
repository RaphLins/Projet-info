package model.map;

import model.Game;
import model.GameObject;
import model.items.Obstacle;

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
		ID = "default_tile";
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
		objects.add(object);
		update();
	}

	public void removeObject(GameObject object){
		objects.remove(object);
		update();
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
	
	public void setX(int x) {
		this.x=x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y=y;
	}
}
