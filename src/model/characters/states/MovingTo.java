package model.characters.states;

import model.Game;
import model.characters.AStar;
import model.characters.Character;
import model.map.Map;
import model.map.Tile;

public class MovingTo extends State {
    Tile target;
    int direction;

    public MovingTo(Character character, int groupID, Tile target){
        super(character,groupID);
        this.target = target;
    }

    public Tile getTarget(){
        return target;
    }

    @Override
    public void run() {
        Map map = Game.getInstance().getMap();
        Tile target = getTarget();
        if(target == null){
            cancel();
            return;
        }
        direction= (new AStar(getCharacter().getPos(), target, map)).getNextStep();	//AStar allows to find the best way to go to the target (it's used to find the direction where the character should go next).
        if(direction==-100){
            cancel();
        }
        else if (direction != -1) {
            Tile nextTile = map.getTileNextTo(getCharacter().getPos(), direction);	//gets the tile corresponding to the direction found with AStar.
            getCharacter().rotateTo(nextTile);
            //nextTile.addObject(getCharacter());
            //for (int i = 0; i < 20; i++) {
            //    getCharacter().offSetInDirection((float) (1.0 / 20.0), direction);
            //    try {
            //        Thread.sleep((long) (50 / 5));
            //    } catch (InterruptedException e) {
            //        //e.printStackTrace();
            //    }
        //}
            //getCharacter().getPos().removeObject(getCharacter());
            getCharacter().setPos(nextTile);
            //getCharacter().resetOffset();
            //the involved tiles and the character's position have to be changed, because the character is now on another tile.
        }
        else {
        	//then direction = -1, as defined in the method getNextStep() in AStar. That means the character is arrived.
            finish();
        }
    }
}
