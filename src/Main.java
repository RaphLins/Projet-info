import java.awt.BorderLayout;

import controller.Keyboard;
import controller.Mouse;
import model.Game;
import view.Clock;
import model.Time;
import view.Window;


public class Main {

	public static void main(String[] args) {
		Window window = new Window("Game");
		Game game = Game.getInstance();
		game.setWindow(window);
		Keyboard keyboard = new Keyboard(Game.getInstance(),window.getMapView());
		Mouse mouse = new Mouse(Game.getInstance());
		window.setKeyListener(keyboard);
		window.setMouseListener(mouse);
	}

}