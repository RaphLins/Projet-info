package view;

import javax.swing.*;
import model.Time;

public class ClockTime implements Runnable {
	
	private Time time;
	private Clock clock;
	
	public ClockTime(Time time , Clock clock) {
		this.time = time;
		this.clock = clock;
	}
	
	public void run() {
		try{
			while(true){
				clock.removeAll();
				JLabel label = new JLabel(convert(time.getMinutes()));
				//System.out.println("Nouveau label créé."+Integer.toString(time.getMinutes()));
				clock.add(label);
				clock.updateUI();
				Thread.sleep(time.getWaitTime());
				}
		}catch(Exception e){};
	}
	
	public String convert(int i) {
		return (Integer.toString(i/60)+":"+Integer.toString(i%60));
	}
	
	

}
