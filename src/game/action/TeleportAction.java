package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.WarpPipe;

/**
 * Special Action for teleporting.
 */
public class TeleportAction extends Action {

    /**
     * warpPipe that teleports the player to the destination
     */
    private WarpPipe warpPipe;

    /**
     * the location of next destination (the other map)
     */
    private Location nextLocation;

    /**
     * the current location
     */
    private Location currentLocation;

    /**
     * the warp pipe in the next destination
     */
    private WarpPipe nextWarpPipe;

    /**
     * Constructor.
     *
     * @param warpPipe that teleports the player to the destination
     * @param nextLocation the location of next destination (the other map)
     * @param currentLocation the current location
     * @param nextWarpPipe the warp pipe in the next destination
     */
    public TeleportAction(WarpPipe warpPipe, Location nextLocation, Location currentLocation, WarpPipe nextWarpPipe){
        this.warpPipe = warpPipe;
        this.nextLocation = nextLocation;
        this.currentLocation = currentLocation;
        this.nextWarpPipe = nextWarpPipe;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // instantly kill Piranha Plant
        if (nextLocation != null){
            if (nextLocation.containsAnActor()){
                nextLocation.map().removeActor(nextLocation.getActor());
            }
            map.moveActor(actor, nextLocation);
            this.nextWarpPipe.recordPreviousLocation(currentLocation, this.warpPipe);
            return actor + " teleported to the lava map";
        }
        map.moveActor(actor, nextLocation);
        return actor + " teleported to the game map";
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor){
        return "teleport to another map";
    }

}
