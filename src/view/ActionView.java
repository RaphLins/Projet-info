package view;
import java.awt.*;
import java.util.ArrayList;

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
			addButton("Eating").addActionListener(e -> {
				c.stopEverything();
				c.eat();
			});
			addButton("Peeing").addActionListener(e -> {
				c.stopEverything();
				c.pee();
			});
			addButton("Washing").addActionListener(e -> {
				c.stopEverything();
				c.wash();
			});
    		addButton("Sleeping").addActionListener(e -> {
				c.stopEverything();
				c.sleep();
			});
    		setLayout(new GridLayout(3,3));
    	}
        setOpaque(false);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

	private JButton addButton(String text){
		JButton button = new JButton(text);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setFocusable(false);
		add(button);
		return button;
	}

}
