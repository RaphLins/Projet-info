package model.characters.states;

import model.Game;
import model.GameObject;
import model.characters.Character;
import model.map.Table;
import model.map.Tile;

public class Eating extends UsingItem {
    public Eating(Character character, int groupID) {
        super(character, groupID,"Stool");
    }

    @Override
    public void init() {
        super.init();
        Table table = null;
        for(int i = 0;i<4;i++){
            Tile tile = Game.getInstance().getMap().getTileNextTo(getCharacter().getPos(),i);
            for(GameObject object:tile.getObjects()){
                System.out.println(object);
                if(object.ID=="Table"){
                    table = (Table) object;
                    getCharacter().rotateTo(tile);
                    getCharacter().placeItem(getCharacter().getItem("Plate"));
                    return;
                }
            }

        }
        if(table==null){
            cancel();
        }
    }

    @Override
    public void run() {
        getCharacter().incrementHunger(5.24);
        if(getCharacter().getHunger()==100){	//the state is stopped if the value of hunger has reached its maximum.
            finish();
        }
    }

    @Override
    public void finish() {
        getCharacter().pickUpItemInFront("Plate");
        super.finish();
    }
}

