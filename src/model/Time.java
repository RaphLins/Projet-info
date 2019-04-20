package model;

import java.util.ArrayList;


public class Time implements Runnable {
	
	private static Time instance = null;

	private static int minutes = 0;
	private static int waitTime = 250;
	private ArrayList<TimeObserver> timeObservers = new ArrayList<>();
	
	private Time() {
		
	}
	
	@Override
	public void run() {
		try{
			while(true){
				if(minutes != 1440){
					//System.out.println(minutes);
					minutes+=1;
					Thread.sleep(waitTime);
				}
				else{
					minutes = 0;
					//System.out.println(minutes);
				}
				for (TimeObserver o : timeObservers) {
					o.timePassed();					
				}
				}
		}catch(Exception e){};
		
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
