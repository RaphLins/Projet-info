package src.view;

import src.items.Drawable;
import src.items.GameObject;
import src.items.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tile {
	
	private int x;
	private int y;
	private ArrayList<GameObject> objects = new ArrayList<>();
	private boolean isWalkable = true;
	public static final int WIDTH =  20;
	public static final int HEIGHT =  20;
	
	public Tile(int i, int j) {
		y = i*WIDTH;
		x = j*HEIGHT;
	}

	public boolean isWalkable(){
		return isWalkable;
	}

	private void updateWalkable(){
		isWalkable = true;
		for(GameObject object : objects){
			if(object instanceof Obstacle){
				isWalkable = false;
			}
		}
	}

	public void addObject(GameObject object){
		objects.add(object);
		updateWalkable();
	}

	public Image getTexture(){
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("res/empty.jpg"));
		} catch (IOException e) {
		}
		return img;
	}

	public void paint(Graphics g){
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("res/empty.jpg"));
		} catch (IOException e) {
		}
		g.drawImage(img, x,y,HEIGHT,WIDTH,null);
		for(Object object: objects){
			if(object instanceof Drawable){
				g.drawImage(((Drawable) object).getTexture(), x,y,HEIGHT,WIDTH,null);
			}

		}
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
