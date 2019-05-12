package model.map.mapObjects;

import model.characters.Character;
import model.items.Item;
import model.map.Obstacle;
import model.map.UsableItem;

public class Bench extends UsableItem implements Obstacle {
    public Bench() {
        super();
        setID("Bench");
        setHeight(2);
        setPrice(50);
        setAccessDirections(new int[]{SOUTH});
    }

    @Override
    public boolean use(Character user) {
        boolean success = super.use(user);
        if(success){
            user.setDirection(NORTH);
        }
        return success;
    }
}
