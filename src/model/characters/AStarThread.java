package model.characters;

import model.Game;
import model.map.Map;
import model.map.Tile;

public class AStarThread implements Runnable{
	private Game g;
	private Character p;
	Tile target;
	private Map map;
	private volatile boolean running = true;

	public AStarThread(Game g, Character p, Tile target, Map map) {
		this.g= g;
		this.p = p;
		this.target = target;
		this.map = map;

	}
	
	@Override
	public void run() {
		int direction = 0;
		while(direction != -1 && running)  {
			direction = (new AStar(p.getPos(), target, map)).getNextStep();
			switch (direction) {
				case 0 : g.movePlayer(1,0); break;
				case 1 : g.movePlayer(0,-1); break;
				case 2 : g.movePlayer(-1,0); break;
				case 3 : g.movePlayer(0,1); break;
			}

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void terminate(){
		running = false;
	}
		

}
