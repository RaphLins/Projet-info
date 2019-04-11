package model;

import java.util.ArrayList;


public class Time implements Runnable {
	
	private static Time instance = null;
    private static boolean started = false;
	
	private static int minutes = 0;
	private static int waitTime = 350;
	private ArrayList<TimeObserver> timeObservers = new ArrayList<TimeObserver>();
	
	private Time() {
		
	}
	
	@Override
	public void run() {
		try{
			while(true){
				if(minutes != 1440){
					minutes+=1;
					Thread.sleep(waitTime);
					//System.out.println(minutes);
				}
				else{
					minutes = 0;
					//System.out.println(minutes);
				}
				for (TimeObserver o : timeObservers) {
					o.timePassed();					
				}
				Game.getInstance().getWindow().update();	
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
	            started = true;
	        }
	        return instance;
	 }
	 
	 public static boolean isStarted(){
	        return started;
	    }
	 
	 public ArrayList<TimeObserver> getList(){
		 return timeObservers;
	 }

}
