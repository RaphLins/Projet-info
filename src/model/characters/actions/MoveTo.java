package model.characters.actions;

import model.Game;
import model.GameObject;
import model.characters.AStar;
import model.characters.Character;
import model.map.Map;
import model.map.Tile;

public class MoveTo extends Action{
    private Game game = Game.getInstance();
    protected Map map = game.getMap();
    Tile target = null;
    int direction;


    public MoveTo(Character character, Tile target){
        super(character);
        this.target = target;
    }

    public MoveTo(Character character, String objectType){
        super(character);
        Tile closest = map.getClosestTile(getCharacter().getPos(), objectType, 50);
        if(closest==null){
            System.out.println("found none");
            character.stopEverything();
        }
        else{
            target = closest;
        }
    }

    public Tile getTarget(){
        return target;
    }

    @Override
    public void performAction() {
        Tile target = getTarget();
        if(target == null){
            direction =-1;
        }
        else {
            direction= (new AStar(getCharacter().getPos(), target, map)).getNextStep();
        }
        if (direction != -1) {
            Tile nextTile = map.getTileNextTo(getCharacter().getPos(), direction);
            getCharacter().rotateTo(nextTile);
            nextTile.addObject(getCharacter());
            //for (int i = 0; i < 20; i++) {
            //    getCharacter().offSetInDirection((float) (1.0 / 20.0), direction);
            //    try {
            //        Thread.sleep((long) (50 / 5));
            //    } catch (InterruptedException e) {
            //        //e.printStackTrace();
            //    }
        //}
            getCharacter().getPos().removeObject(getCharacter());
            getCharacter().setPos(nextTile);
            getCharacter().resetOffset();
        }
        else {
            System.out.println("arrived");
            actionFinished();
        }
    }
}
