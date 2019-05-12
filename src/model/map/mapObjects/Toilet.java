package model.map.mapObjects;

import model.characters.Character;
import model.map.Obstacle;
import model.map.UsableItem;

public class Toilet extends UsableItem implements Obstacle {
    public Toilet() {
        super();
        setID("Toilet");
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
