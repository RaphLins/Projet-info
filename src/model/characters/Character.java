package model.characters;

import model.Game;
import model.GameObject;
import model.ObjectHolder;
import model.Time;
import model.TimeObserver;
import model.items.CarriableItem;
import model.places.Place;
import model.map.Tile;
import model.map.Map;
import java.lang.Math;

import java.util.ArrayList;

public abstract class Character extends GameObject implements Directable, ObjectHolder, TimeObserver {

	private final int DOING_NOTHING = 0;
	private final int MOVING = 1;
	private final int SLEEPING = 2;
	private final int EATING = 3;
	private final int PEEING = 4;
	private final int WASHING = 5;

	private double hunger = 100;
	private double hygiene = 100;
	private double bladder = 100;
	private double energy = 100;
	private Place location;
	private int direction = EAST;
	private MovingThread moveThread;
	private ArrayList<GameObject> inventory = new ArrayList<>();
	private int state = DOING_NOTHING;
	private boolean isArrived = false; //permet de ne pas relancer la m�thode goToClosest(...) � chaque fois que le personnage essaie de faire une action (en fait le personnage relan�ait la m�thode � chaque boucle faite par le temps et ne d�marrait jamais son action vu qu'il �tait constamment en state = moving)

	public Character() {
		Time.getInstance().attach(this);
	}

	public void goTo(Tile target){
		if(target.isWalkable()){
			if(moveThread!=null){
				moveThread.terminate();
			}
			moveThread = new MovingThread(this, target,5);
			Thread thread = new Thread(moveThread);
			thread.start();			
		}
		else{
			rotateTo(target);
		}
	}
	
	public void setState(int state) {
		this.state = state;
	}
	//permet de modifier state dans le movingthread
	

	public void rotateTo(Tile target) {
		int x= target.getX()-getPosX();
		int y= target.getY()-getPosY();
		if(y<x && y<-x)
			direction = NORTH;
		else if(y>x && y>-x)
			direction = SOUTH;
		else if(y<x && y>-x)
			direction = EAST;
		else if(y>x && y<-x)
			direction = WEST;
		getPos().update();
	}
	
	public void goToClosest(String s) {
		state = MOVING; //du coup le personnage ne peut pas encore d�marrer son action car pour �a il faut que state = doingnothing
		Map map = Game.getInstance().getMap();
		ArrayList<Tile> tilesAround = Game.getInstance().getTilesAround(this);
		ArrayList<Tile> possibleTargets =  new ArrayList<Tile>();
		for(Tile tile : tilesAround) {
			ArrayList<GameObject> objects = tile.getObjects();
			for(GameObject o : objects) {
				if (o.ID.equals(s)) {
					possibleTargets.add(o.getPos());
				}
			}
		}
		//on a cr�� une liste contenant les cases autour du personnage contenant l'objet n�cessaire pour l'action
		Tile target = getClosestTile(possibleTargets); //choisit celle qui est la plus proche � vol d'oiseau
		if(target.isWalkable()) {
			goTo(target);
		}
		else {
			for(int i=0 ; i<4; i++) {
				Tile target2 = map.getTileNextTo(target,i);
				if(target2.isWalkable()) {
					goTo(target2);
					break;
				} //si le personnage ne peut pas aller sur la case, il va chercher � aller sur celles � c�t� de l'objet
			}			
		}
	}
	
	public Tile getClosestTile(ArrayList<Tile> possibleTargets) { //utilise les coordonn�es des cases contenant l'objet qu'on cherche pour d�terminer celle qui est la plus proche � vol d'oiseau
		Tile res = possibleTargets.get(0);
		for (Tile tile : possibleTargets) {
			float currentDistance = (float) Math.pow(Math.pow(res.getX()-getPos().getX(),2) + Math.pow(res.getY()-getPos().getY(),2),0.5);
			float distance = (float) Math.pow(Math.pow(tile.getX()-getPos().getX(),2) + Math.pow(tile.getY()-getPos().getY(),2),0.5);
			if(distance<currentDistance) {
				res = tile;
			}
		}
		return res;
	}

	
	public boolean isArrived(Tile target) {
		if((getPos().getX() == target.getX() && getPos().getY() == target.getY())) {
			isArrived = true;
		}
		return isArrived; //isArrived est utilis� dans movingThread pour modifier state quand le personnage est bien arriv�
		//isArrived permet aussi de ne pas lancer goToClosest en boucle (cf les if dans pee, eat,...)
	}
	
	
	@Override
	public int getDirection() {
		return direction;
	}

	public void eat() {
		if(state == DOING_NOTHING && isArrived == false) {
			goToClosest("Bed");			//la m�thode ne se red�clenche pas � chaque boucle si le personnage est d�j� arriv�. Le personnage doit aussi �tre en train de ne rien faire
		}
		if(state == DOING_NOTHING) {	//state passe de moving � doingnothing quand le personnage est arriv�. goToClosest ne se red�clenche pas car isArrived = true
			System.out.println("je peux manger");
			state = EATING;
			isArrived = false; // permet au personnage de r�utiliser goToClosest pour une autre action
		}
//		}
	}

	public void wash() {
		state = WASHING;
	}

	public void pee() {
		if(state == DOING_NOTHING && isArrived == false) {
			System.out.println("OK");
			goToClosest("Wardrobe");
		}
		if(state == DOING_NOTHING) {
			System.out.println("je peux faire pipi");
			state = PEEING;
			isArrived = false;
		}
	}

	public void sleep() {
		if(state == DOING_NOTHING && isArrived == false) {
			goToClosest("Bed");
		}
		if(state == DOING_NOTHING) {
			System.out.println("je peux dormir");
			state = SLEEPING;
			isArrived = false;
		}
	}

	public void fetchItem() {

	}

	public void setDirection(int direction) {
		this.direction=direction;
	}

	public double getEnergy() {
		return energy;
	}

	public double getHunger() {
		return hunger;
	}

	public double getBladder() {
		return bladder;
	}

	public double getHygiene() {
		return hygiene;
	}

	public void carryItem(CarriableItem item){
		inventory.add((GameObject) item);
	}

	public ArrayList<GameObject> getInventory() {
		return inventory;
	}

	public void incrementBladder(double i) {
		bladder = Math.max(Math.min(bladder+i,100),0);
		if (bladder<=25) {
			pee();
		}
	}
	
	public void incrementEnergy(double i) {
		energy = Math.max(Math.min(energy+i,100),0);
		if (energy<=25) {
			sleep();
		}
	}
	
	public void incrementHunger(double i) {
		hunger = Math.max(Math.min(hunger+i,100),0);
		if (hunger<=25) {
			eat();
		}
	}
	
	public void incrementHygiene(double i) {
		hygiene = Math.max(Math.min(hygiene+i,100),0);
		if (hygiene<=25) {
			wash();
		}
	}
	
	@Override
	public void timePassed() {
		incrementBladder(-0.8);
		incrementEnergy(-0.14);
		incrementHunger(-0.34);
		incrementHygiene(-0.07);
			switch (state){
				case DOING_NOTHING:
					break;
				case EATING:
					incrementHunger(5.24);
					if(getHunger()==100){
						state = DOING_NOTHING;
					}
					break;
				case WASHING:
					incrementHygiene(10.07);
					if(getHygiene()==100){
						state = DOING_NOTHING;
					}
					break;
				case PEEING:
					incrementBladder(50.8);
					if(getBladder()==100){
						state = DOING_NOTHING;
					}
					break;
				case SLEEPING:
					incrementEnergy(0.35);
					if(getEnergy()==100){
						state = DOING_NOTHING;
					}
					break;
			}



		if (this == Game.getInstance().getSelectedObject()) {
			Game.getInstance().getWindow().getStatusView().redraw();
		}
	}

}
