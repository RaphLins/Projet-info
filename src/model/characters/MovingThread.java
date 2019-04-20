package model.characters;

import model.Game;
import model.map.Map;
import model.map.Tile;

public class MovingThread implements Runnable{
	private Game game = Game.getInstance();
	private Map map = game.getMap();
	private Character p;
	Tile target;
	private volatile boolean running = true;
	float speed;

	public MovingThread(Character p, Tile target, float speed) {
		this.p = p;
		this.target = target;
		this.speed = speed;
	}
	
	@Override
	public void run() {
		int direction = 0;
		while(direction != -1 && running)  {
			synchronized (p) {
				//p.setState(1);
				direction = (new AStar(p.getPos(), target, map)).getNextStep();
				if (direction != -1) {
					Tile nextTile = map.getTileNextTo(p.getPos(), direction);
					p.rotateTo(nextTile);
					nextTile.addObject(p);
					for (int i = 0; i < 20; i++) {
						p.offSetInDirection((float) (1.0 / 20.0), direction);
						try {
							Thread.sleep((long) (50 / speed));
						} catch (InterruptedException e) {
							//e.printStackTrace();
						}
					}
					p.getPos().removeObject(p);
					p.setPos(nextTile);
					p.resetOffset();
				}
			}
		}
	}

	public void terminate(){
		this.running=false;
	}
		

}
