package view;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Time;
import model.TimeObserver;


public class Clock extends JPanel implements TimeObserver{
	
	private Time time = Time.getInstance();
	
	
	public Clock() {
		//Thread clockTime = new Thread (new ClockTime(time, this));
		//clockTime.start();
		attach();
		this.setPreferredSize(new Dimension(260, 100));
        this.setBackground(new Color( 253, 135, 208 ));
        this.setOpaque(true);
        
	}
	@Override
	public void timePassed() {
		removeAll();
		JLabel label = new JLabel(convert(time.getMinutes()));
		//System.out.println("Nouveau label créé."+Integer.toString(time.getMinutes()));
		add(label);
		updateUI();
	}
	
	public String convert(int i) {
		return (Integer.toString(i/60)+":"+Integer.toString(i%60));
	}
	
	public void attach() {
		time.getList().add(this);
	}

}
