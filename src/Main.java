import java.awt.BorderLayout;

import controller.Keyboard;
import controller.Mouse;
import model.Game;
import view.Clock;
import model.Time;
import view.Window;


public class Main {

	public static void main(String[] args) {
		//Time time = new Time(350);
		Thread gameTime = new Thread(Time.getInstance());
		gameTime.start();
		Window window = new Window("Game");
		//Clock clock = new Clock(time);
		//window.getGroupPanel().add(clock, BorderLayout.PAGE_START);
		Game game = Game.getInstance();
		//game.setGameTime(time);
		game.setWindow(window);
		Keyboard keyboard = new Keyboard(Game.getInstance(),window.getMapView());
		Mouse mouse = new Mouse(Game.getInstance());
		window.setKeyListener(keyboard);
		window.setMouseListener(mouse);
	}

}