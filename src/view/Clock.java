package view;

import java.awt.*;
import javax.swing.JPanel;
import model.Time;


public class Clock extends JPanel{
	
	
	public Clock(Time time) {
		Thread clockTime = new Thread (new ClockTime(time, this));
		clockTime.start();
		this.setPreferredSize(new Dimension(260, 100));
        this.setBackground(new Color( 253, 135, 208 ));
        this.setOpaque(true);
        
	}
	

}
