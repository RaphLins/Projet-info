package controller;

import model.Game;
import model.GameObject;
import model.characters.Character;
import model.map.Map;
import view.MapView;
import view.Window;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse {
    private Game game;

    public Mouse(Game game) {
        this.game = game;
    }

	public void mapEventRightClick(int x, int y) {
		synchronized(game) {
			GameObject selected = game.getSelectedObject();
			if(selected instanceof Character){
				((Character)selected).goTo(game.getMap().getTileAt(x,y));
			}
		}
	}
	public void mapEventLeftClick(int x, int y) {
		synchronized(game) {
			GameObject selected = game.getMap().getTileAt(x,y).getTopObject();
			for(int i =-1; i<=1;i++){
				for(int j =-1; j<=1;j++){
					GameObject object = game.getMap().getTileAt(x+i,y+j).getTopObject();
					if(object!=null && selected==null){
						selected=object;
					}
				}
			}
			game.selectObject(selected);
			game.getWindow().getMapView().repaint();
			game.getWindow().getStatusView().redraw();
		}
	}
}
