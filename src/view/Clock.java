package view;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Time;
import model.TimeObserver;


public class Clock extends JPanel implements TimeObserver{
	
	private Time time = Time.getInstance();
	JLabel label = new JLabel();
	
	
	public Clock() {
		Time.getInstance().attach(this);
		this.setPreferredSize(new Dimension(260, 100));
        this.setBackground(new Color( 253, 135, 208 ));
        this.setOpaque(true);
		add(label);
        
	}
	@Override
	public void timePassed() {
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		label.setText("Time: "+convert(time.getMinutes()));
	}

	public String convert(int i) {
		return ((i/60)+":"+(i%60));
	}

}
