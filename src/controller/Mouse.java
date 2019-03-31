package controller;

import model.Game;

public class Mouse {
    private Game game;

    public Mouse(Game game) {
        this.game = game;
    }

	public void mapEventRightClick(int x, int y) {
		synchronized(game) {
			game.getPlayer().goTo(game.getMap().getTileAt(x,y));
		}
	}
	public void mapEventLeftClick(int x, int y) {
		synchronized(game) {
		}
	}
}
