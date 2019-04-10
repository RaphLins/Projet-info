package model;


public class Time implements Runnable {
	
	private static int minutes = 0;
	private static int waitTime;
	
	public Time(int waitTime) {
		this.waitTime = waitTime;
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
				else if (minutes == 1440){
					minutes = 0;
					//System.out.println(minutes);

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
	
	

}
