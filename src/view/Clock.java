package view;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Time;
import model.TimeObserver;


public class Clock extends JPanel implements TimeObserver{

	JLabel label = new JLabel();
	
	public Clock() {
		Time.getInstance().attach(this);
		this.setPreferredSize(new Dimension(260, 30));
        this.setOpaque(false);
		add(label);
	}

	@Override
	public void timePassed() {
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		label.setText("Time: "+convert(Time.getInstance().getMinutes()));
	}

	public String convert(int i) {
		return ((i/60)+":"+(i%60));
	}

}
