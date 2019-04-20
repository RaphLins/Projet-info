package model.characters.actions;

import model.Game;
import model.characters.AStar;
import model.characters.Character;
import model.map.Map;
import model.map.Tile;

public class Move extends Action{
    private Game game = Game.getInstance();
    private Map map = game.getMap();
    Tile target;
    private String objectType;
    int direction;

    public Move(Character character, String objectType){
        super(character);
        this.objectType = objectType;
    }
    public Move(Character character, Tile target){
        super(character);
        this.target = target;
    }

    public Tile getTarget(){
        if (target != null)return target;
        else {
            Tile tile = map.getClosestTile(getCharacter().getPos(), objectType, 50);
            if(tile.isWalkable()) {
                return tile;
            }
            else {
                for(int i=0 ; i<4; i++) {
                    Tile target2 = map.getTileNextTo(tile,i);
                    if(target2.isWalkable()) {
                        return target2;
                    } //si le personnage ne peut pas aller sur la case, il va chercher � aller sur celles � c�t� de l'objet
                }
            }
            System.out.println("found none");
            actionFinished();
            return null;
        }
    }

    @Override
    public void performAction() {
        Tile target = getTarget();
        if(target == null)direction =-1;
        else direction= (new AStar(getCharacter().getPos(), target, map)).getNextStep();
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
