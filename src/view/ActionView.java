package view;

import model.Game;
import model.characters.Character;
import model.characters.*;
import model.map.GameObject;

import javax.swing.*;
import java.awt.*;

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
				addButton("<html>Read Magic<br> Book</html>").addActionListener(e->{
					c.stopEverything();
					((ChildWizard)c).readMagicBook();
				});
			}
			if(selected instanceof AdultWizard){
				addButton("<html>Read Magic<br> Book</html>").addActionListener(e->{
					c.stopEverything();
					((AdultWizard)c).readMagicBook();
				});
			}

			if(selected instanceof Adult) {
				addButton("<html>Go to<br> work</html>").addActionListener(e->{
					c.stopEverything();
					((Adult)c).work();
				});
			}
			
			if(selected instanceof Child) {
				addButton("<html>Go to<br> school</html>").addActionListener(e->{
					c.stopEverything();
					((Child)c).goToSchool();
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
		button.setMargin(new Insets(1, 1, 1, 1) );
		button.setFont(new Font("default", Font.BOLD, 11));
		add(button);
		return button;
	}

}
