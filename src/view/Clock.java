package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Game;
import model.TimeObserver;


public class Clock extends JPanel implements TimeObserver{

	private JLabel timeDisplay = new JLabel();
	private JPanel controlButtons = new JPanel();
	private String stateSring = "";
	
	public Clock() {
		this.setPreferredSize(new Dimension(260, 30));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setOpaque(false);
		controlButtons.setOpaque(false);
		timeDisplay.setFont(new Font("default", Font.BOLD, 18));
		timeDisplay.setHorizontalAlignment(SwingConstants.CENTER);

		setLayout(new BorderLayout());
		add(timeDisplay, BorderLayout.PAGE_START);
		add(controlButtons, BorderLayout.PAGE_END);

		addButton("<html>&#9205</html>  ").addActionListener(e -> {
			Game.getInstance().getTime().setWaitTime(200);
			Game.getInstance().getTime().start();
			stateSring = "";
		});
		addButton("<html>&#9208</html>").addActionListener(e -> {
			Game.getInstance().getTime().pause();
			stateSring = " (paused)";
		});
		addButton("<html>&#9197</html>").addActionListener(e -> {
			Game.getInstance().getTime().setWaitTime(100);
			Game.getInstance().getTime().start();
			stateSring = " (x2)";
		});
		addButton("<html>&#9197&#9197</html>").addActionListener(e -> {
			Game.getInstance().getTime().setWaitTime(50);
			Game.getInstance().getTime().start();
			stateSring = " (x4)";
		});
	}

	@Override
	public void timePassed() {
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		timeDisplay.setText("Time: "+convert(Game.getInstance().getTime().getMinutes())+stateSring);
	}

	public String convert(int i) {
		return (String.format("%02d", i/60)+":"+String.format("%02d", i%60));
	}

	private JButton addButton(String text){
		JButton button = new JButton(text);
		button.setFont(new Font("default", Font.PLAIN, 17));
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setFocusable(false);
		controlButtons.add(button);
		return button;
	}

}
