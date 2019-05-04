import controller.Keyboard;
import controller.Mouse;
import model.Game;
import view.Window;


public class Main {

	public static void main(String[] args) {
		Window window = new Window("Game");
		Game game = Game.getInstance();
		game.setWindow(window);
		game.newGame();
		Keyboard keyboard = new Keyboard(window);
		Mouse mouse = new Mouse();
		window.setKeyListener(keyboard);
		window.setMouseListener(mouse);
	}

}