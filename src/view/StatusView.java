package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import model.Game;
import model.GameObject;
import model.ObjectHolder;
import model.characters.Character;
import model.characters.Wizard;

public class StatusView extends JPanel {
	private int BAR_LENGTH = 215;
	private int BAR_WIDTH = 15;
	private int AVATAR_SIZE = 100;
	private TextureHashMap textures = new TextureHashMap();

    public StatusView() {
        this.setPreferredSize(new Dimension(260, 600));
        this.setOpaque(false);
    }
    
	public void paint(Graphics g) {
		super.paintComponent(g);
        GameObject selected = Game.getInstance().getSelectedObject();
        if(selected==null){
            return;
        }
        int y = 20;
        g.setColor(Color.BLACK);
        g.setFont(new Font("default", Font.BOLD, 19));
        g.drawString(selected.ID, 3, y);
        y+=20;
        if(selected instanceof Character){
            Character character = (Character)selected;
            g.setColor(Color.BLUE);
            y+=10;
            g.drawImage(textures.getAvatar(character.ID),80,y, AVATAR_SIZE, AVATAR_SIZE,null);

            // bars
            y+=175;
            drawBar("Energy",character.getEnergy(),0,y,g);
            y+=50;
            drawBar("Hunger",character.getHunger(),0,y,g);
            y+=50;
            drawBar("Hygiene",character.getHygiene(),0,y,g);
            y+=50;
            drawBar("Bladder",character.getBladder(),0,y,g);
            y+=50;
            drawBar("Happiness",character.getHappiness(),0,y,g);
            if(character instanceof Wizard){
                y+=50;
                drawBar("Magic Power",((Wizard)character).getMagicPower(),0,y,g);
            }
            y+=50;

        }

        if(selected instanceof ObjectHolder){
            int x = 3;
            g.setColor(Color.BLACK);
            g.setFont(new Font("default", Font.BOLD, 16));
            g.drawString("Inventory", x, y);
        }
        setPreferredSize(new Dimension(260, y+20));

    }

    private void drawBar(String name, double value, int x, int y, Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("default", Font.BOLD, 16));
        g.drawString(name, x+3, y);
        g.setColor(new Color( 148, 49, 38 ));
        g.fillRect(x+20, y+10, BAR_LENGTH, BAR_WIDTH);
        g.setColor(new Color(40, 180, 99));
        int length_ok = (int) Math.round(BAR_LENGTH*value/100);
        g.fillRect(x+20, y+10, length_ok, BAR_WIDTH);
    }

    public void redraw() {
        this.repaint();
    }
}
