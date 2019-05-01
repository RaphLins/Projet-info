package view;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Graphics;

import javax.swing.*;

import model.GameObject;
import model.Game;
import model.characters.Character;

public class ActionView extends JPanel{
	
	
    public ActionView() {
    	setPreferredSize(new Dimension(260, 50));
    	GameObject selected = Game.getInstance().getSelectedObject();
    	if(selected instanceof Character) {
    		Character c = (Character)selected;
    		JButton eatButton = new JButton("Eat");
    		eatButton.addActionListener(e -> {
    			c.stopEverything();
    			c.eat();          
    		});
    		JButton peeButton = new JButton("Pee");
    		peeButton.addActionListener(e -> {
    			c.stopEverything();
    			c.pee();          
    		});
    		JButton washButton = new JButton("Wash");
    		washButton.addActionListener(e -> {
    			c.stopEverything();
    			c.wash();          
    		});
    		JButton sleepButton = new JButton("Sleep");
    		sleepButton.addActionListener(e -> {
    			c.stopEverything();
    			c.sleep();          
    		}); 
    		setLayout(new GridLayout(3,3));
            add(eatButton);
            add(peeButton);
            add(washButton);
            add(sleepButton);
            add(new JButton("a"));
            add(new JButton("b"));
            add(new JButton("c"));
            add(new JButton("d"));
    	}
        setOpaque(false);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

}
