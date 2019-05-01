package model;

import java.util.ArrayList;


public class Time implements Runnable {
	
	private static Time instance = null;

	private static int minutes = 0;
	private static int waitTime = 850;
	private ArrayList<TimeObserver> timeObservers = new ArrayList<>();
	long lastUpdate = 0;
	
	private Time() {
	}
	
	@Override
	public void run() {
		while(true){
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
	
	public int getMinutes() {
		return minutes;
	}
	
	public int getWaitTime() {
		return waitTime;
	}
	
	 public static Time getInstance(){
	        if(instance == null){
	            instance = new Time();
	        }
	        return instance;
	 }

	 public void attach(TimeObserver o){
		timeObservers.add(o);
	 }

}
