package src.view;

import src.items.Item;
import src.items.Obstacle;

import java.awt.*;
import java.util.ArrayList;

public class Tile {
	
	private int x;
	private int y;
	private ArrayList<Item> items = new ArrayList<>();
	private boolean isWalkable = true;
	
	public Tile(int x , int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isWalkable(){
		return isWalkable;
	}

	private void updateWalkable(){
		isWalkable = true;
		for(Item item : items){
			if(item instanceof Obstacle){
				isWalkable = false;
			}
		}
	}

	public void addItem(Item item){
		items.add(item);
		updateWalkable();
	}

	public Color getColor(){
		return new Color((int)(Math.random() * 0x1000000));
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
