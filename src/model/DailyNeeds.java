package model;

import java.util.ArrayList;
import model.Time;
import model.characters.Character;

public class DailyNeeds implements Runnable {
	
	private Time time;
	private ArrayList<Character> family;
	private Game game;
	
	public DailyNeeds(Time time , ArrayList<Character> family, Game game) {
		this.time = time;
		this.family = family;
		this.game = game;
	}
	
	public void run() {
		try{
			while(true){
				for (Character c : family) {
					c.decrease(1,2,3,4);		
					c.actions();
				}
				
				game.getWindow().update();
				Thread.sleep(time.getWaitTime());
			}
		}catch(Exception e){};
	}
	
	
}
	
	


