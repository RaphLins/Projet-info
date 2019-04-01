package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Game;
import model.characters.Character;

public class StatusView extends JPanel {
	private Character p;
	private int BAR_LENGTH = 60;
	private int BAR_WIDTH = 20;
	private int AVATAR_SIZE = 100;

    public StatusView() {
        this.setPreferredSize(new Dimension(380, 600));
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);
        p= Game.getInstance().getPlayer();
    }
    
	public void paint(Graphics g) {
		super.paintComponent(g);
		// draw avatar
        g.setColor(Color.BLUE);
        g.fillRect(150, 25, AVATAR_SIZE, AVATAR_SIZE);

		// bars 
        // Energy 
        g.setColor(Color.BLACK);
        g.drawString("Energy", 0, 200);
        g.setColor(Color.RED);
        g.fillRect(0, 200, BAR_LENGTH, BAR_WIDTH);
        g.setColor(Color.GREEN);
        int length_ok = (int) Math.round(BAR_LENGTH*p.getEnergy());
        g.fillRect(0, 200, length_ok, BAR_WIDTH);
    }

    public void redraw() {
        this.repaint();
    }

	public void setPlayer(Character p2) {
		this.p = p2;
	}
}
