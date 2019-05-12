package controller;

import model.Game;
import model.characters.Character;
import model.map.GameObject;
import model.map.Tile;
import model.map.mapObjects.Decoration;

public class Mouse {

    public Mouse() {
    }

	public void mapEventRightClick(int x, int y) {//moves the selected character
		Game game = Game.getInstance();
		GameObject selected = game.getSelectedObject();
		if(selected instanceof Character && game.getFamily().contains(selected)){
			((Character)selected).stopEverything();
			((Character)selected).goTo(game.getMap().getTileAt(x,y));
			}
	}
	public void mapEventLeftClick(int x, int y) {//select an object
		Game game = Game.getInstance();
		GameObject dragged = game.getDraggedObject();
		boolean mapEditable = game.isMapEditable();
		if(dragged != null){
			Tile target = game.getMap().getTileAt(x,y);
			if(target.isWalkable()){
				if(target.getLocation() == Game.getInstance().getFamilyHouse() || mapEditable){
					dragged.setPos(target);
					game.setDraggedObject(null);
				}
				Game.getInstance().getWindow().message("You can only build in your house");
			}
			else {
				Game.getInstance().getWindow().message("Can't place there.");
			}
		}
		else{
			GameObject selected = game.getMap().getTileAt(x,y).getTopObject();
			for(int i =-1; i<=1;i++){
				for(int j =-1; j<=1;j++){
					GameObject object = game.getMap().getTileAt(x+i,y+j).getTopObject();
					if(object!=null && selected==null && object instanceof Character){
						selected=object;
					}
				}
			}
			if(!(selected instanceof Decoration)){
				game.selectObject(selected);
			}
		}
	}
}
