package model.characters.states;

import model.Game;
import model.characters.Character;
import model.map.GameObject;
import model.map.UsableItem;

import java.util.ArrayList;

abstract public class UsingItem extends State {
    private UsableItem item;
    private Class type;

    public UsingItem(Character character, int groupID, Class type) {
        super(character,groupID);
        this.type = type;
    }

    @Override
    public void init() {
        super.init();
        ArrayList<GameObject> list = Game.getInstance().getMap().getNearbyObjects(getCharacter().getPos(),type,1);
        if(list.isEmpty()){
            super.cancel();//if can't find any items
        }
        else {
            item = (UsableItem) list.get(0);
            synchronized (getCharacter()){
                if(!item.use(getCharacter())){//try to use item
                    item=null;
                    super.cancel();//if the item was already in use by another character
                }
            }
        }
    }

    @Override
    public void finish() {
        if(item!=null){
            item.stopUsing();
        }
        super.finish();
    }

    @Override
    public void cancel() {
        if(item!=null){
            item.stopUsing();
            item = null;
        }
        super.cancel();
    }
}