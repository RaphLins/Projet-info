package view;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import model.GameObject;
import model.Game;
import model.characters.AdultWizard;
import model.characters.Character;
import model.characters.ChildWizard;
import model.characters.Wizard;

public class ActionView extends JPanel{
	
	
    public ActionView() {
    	setPreferredSize(new Dimension(260, 50));
    	GameObject selected = Game.getInstance().getSelectedObject();
    	if(selected instanceof Character && Game.getInstance().getFamily().contains(selected)) {
    		Character c = (Character)selected;
			addButton("Eat").addActionListener(e -> {
				c.stopEverything();
				c.eat();
			});
			addButton("Pee").addActionListener(e -> {
				c.stopEverything();
				c.pee();
			});
			addButton("Wash").addActionListener(e -> {
				c.stopEverything();
				c.wash();
			});
    		addButton("Sleep").addActionListener(e -> {
				c.stopEverything();
				c.sleep();
			});
    		addButton("Socialize").addActionListener(e->{
    			Game.getInstance().getWindow().remove(this);
    			Game.getInstance().getWindow().showSocializeView();
    		});
    		if(selected instanceof ChildWizard){
				addButton("Read Magic Book").addActionListener(e->{
					c.stopEverything();
					((ChildWizard)c).readMagicBook();
				});
			}
			if(selected instanceof AdultWizard){
				addButton("Read Magic Book").addActionListener(e->{
					c.stopEverything();
					((AdultWizard)c).readMagicBook();
				});
			}

    		setLayout(new GridLayout(3,3));
    	}
        setOpaque(false);
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
