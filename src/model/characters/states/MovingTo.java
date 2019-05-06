package model.characters.states;

import model.Animation;
import model.Game;
import model.characters.AStar;
import model.characters.Character;
import model.map.Map;
import model.map.Tile;

public class MovingTo extends State implements Animation {
    private Tile target;
    private boolean teleport;
    private int direction;
    private Tile lastPos = null;

    private float animOffset = 0;

    public MovingTo(Character character, int groupID, Tile target){
        super(character,groupID);
        this.target = target;
    }

    public MovingTo(Character character, int groupID, Tile target, boolean teleport){
        this(character,groupID,target);
        this.teleport = teleport;
    }

    public Tile getTarget(){
        return target;
    }

    public void setTarget(Tile target) {
        this.target = target;
    }

    @Override
    public void init() {
        Game.getInstance().getTime().attach(this);
        super.init();
        if(teleport && target.distanceTo(getCharacter().getPos())>10){

        }

    }

    @Override
    public void run() {
        Tile target = getTarget();
        if(target == null){
            cancel();
            return;
        }
        Map map = Game.getInstance().getMap();
        direction= (new AStar(getCharacter().getPos(), target, map)).getNextStep();
        //AStar allows to find the best way to go to the target (it's used to find the direction where the character should go next).
        if(direction==-100){
            cancel();
        }
        else if (direction != -1) {
            //for animation
            if(lastPos!=null){
                lastPos.removeObject(getCharacter());
            }
            lastPos = getCharacter().getPos();

            //main code
            Tile nextTile = map.getTileNextTo(getCharacter().getPos(), direction);	//gets the tile corresponding to the direction found with AStar.
            getCharacter().rotateTo(nextTile);
            getCharacter().setPos(nextTile);

            //for animation
            lastPos.addObject(getCharacter());
            animOffset = -1;
            getCharacter().resetOffset();
            getCharacter().setOffsetInDirection(animOffset,direction);
        }
        else {
            //then direction = -1, as defined in the method getNextStep() in AStar. That means the character is arrived.
            finish();
        }
    }

    @Override
    public void animate(float timeFraction) {
        if(animOffset+timeFraction>0){
            animOffset=0;
            getCharacter().resetOffset();
        }
        else {
            animOffset+=timeFraction;
            getCharacter().setOffsetInDirection(animOffset,direction);
        }
        getCharacter().getPos().update();
    }

    @Override
    public void finish() {
        //end animation
        if(lastPos!=null){
            lastPos.removeObject(getCharacter());
        }
        getCharacter().resetOffset();
        Game.getInstance().getTime().detach(this);

        super.finish();
    }

    @Override
    public void cancel() {
        //end animation
        if(lastPos!=null){
            lastPos.removeObject(getCharacter());
        }
        getCharacter().resetOffset();
        Game.getInstance().getTime().detach(this);

        super.cancel();
    }
}
