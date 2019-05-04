package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Time implements Runnable, Serializable {

	private int minutes = 0;
	private int waitTime = 60;
	private ArrayList<TimeObserver> timeObservers = new ArrayList<>();
	long lastUpdate = 0;
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
			}
		}
		
	}
	
	public int getMinutes() {
		return minutes;
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

	public void pause(){
		running = false;
	}

	public void start(){
		running = true;
	}
}
