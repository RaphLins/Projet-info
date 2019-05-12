package model.time;

import java.io.Serializable;
import java.util.ArrayList;


public class Time implements Serializable,Runnable {

	private int minutes = 480;
	public final static int DEFAULT_WAIT_TIME = 200;
	private int waitTime = DEFAULT_WAIT_TIME;
	private int animWaitTime = 40;
	private ArrayList<TimeObserver> timeObservers = new ArrayList<>();
	private ArrayList<Animation> animations = new ArrayList<>();
	private long lastUpdate = 0;
	private long lastAnimUpdate = 0;
	volatile boolean running = true;


	public Time() {
	}
	
	@Override
	public void run() {
		while(true){
			if(running){
				long currentTime = System.currentTimeMillis();
				if(currentTime-lastUpdate>=waitTime){
					if(minutes != 1440){
						minutes+=1;
					}
					else{
						minutes = 0;
					}
					for (TimeObserver o : timeObservers) {
							o.timePassed();
						}

					lastUpdate = currentTime;
				}

				if(currentTime-lastAnimUpdate>=animWaitTime){
					for (Animation a:animations) {
						a.animate((float)animWaitTime/waitTime);
					}
					lastAnimUpdate = currentTime;
				}
			}
		}
		
	}
	
	public int getMinutes() {
		return minutes;
	}

	public int getHours() {
		return minutes/60;
	}
	
	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public void attach(TimeObserver o){
		timeObservers.add(o);
	 }

	public void attach(Animation a){
		animations.add(a);
	}

	public void pause(){
		running = false;
	}

	public void start(){
		running = true;
	}

	public void detach(Animation a) {
		animations.remove(a);
	}
}
