package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.action.JumpAction;
import game.action.Resettable;
import game.action.TeleportAction;
import game.actor.PiranhaPlant;

/**
 * Class representing a warp pipe that helps the player to teleport.
 */
public class WarpPipe extends HighGround implements Resettable{

    /**
     * the game's turn
     */
    private int turn = 0;

    /**
     * the previous location that will be the next destination of the warp pipe in the lava map
     */
    private Location previousLocation;

    /**
     * the next warp pipe that will be the next destination of the warp pipe
     */
    private WarpPipe nextWarpPipe;

    /**
     * the next destination
     */
    private Location nextLocation;

    /**
     * Constructor.
     *
     * @param nextLocation the next destination
     * @param nextWarpPipe the next warp pipe
     */
    public WarpPipe(Location nextLocation, WarpPipe nextWarpPipe) {
        super('C');
        this.nextLocation = nextLocation;
        this.nextWarpPipe = nextWarpPipe;
        this.registerInstance();
    }

    /**
     * Records the previous location that occurred teleportation from the game map to the lava map
     * so that the warp pipe in the lava map goes back to the location that the player came from.
     *
     * @param location the location of the warp pipe in the game map
     * @param warpPipe the warp pipe in the game map
     */
    public void recordPreviousLocation(Location location, WarpPipe warpPipe){
        this.previousLocation = location;
        this.nextWarpPipe = warpPipe;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();

        if (!location.containsAnActor()) {
            actionList.add(new JumpAction(this, location, direction));
        } else {
            if (this.nextLocation == null){
                actionList.add(new TeleportAction(this, this.previousLocation, location, this.nextWarpPipe));
            } else {
                actionList.add(new TeleportAction(this, this.nextLocation, location, this.nextWarpPipe));
            }
        }
        return actionList;
    }

    @Override
    public double getSuccessRate() {
        return 1;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void tick(Location location) {
        if (turn == 1) {
            if (!location.containsAnActor()){
             location.addActor(new PiranhaPlant());}
        }
         turn += 1;
    }

    @Override
    public String toString(){
        return "Warp pipe";
    }


    @Override
    public void resetInstance() {
        turn = 0;
        this.hasCapability(Status.RESETCOMPLETE);
    }


}
