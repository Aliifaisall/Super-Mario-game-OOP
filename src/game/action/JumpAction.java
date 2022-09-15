package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Special Action for the Actor (player) to jump the jumpable ground.
 */
public class JumpAction extends Action {

    /**
     * The High Ground that is to be jumped
     */
    protected Jumpable jumpable;

    /**
     * The location referring to the jumpable ground
     */
    protected Location jumpableLocation;

    /**
     * The direction of jumping action
     */
    protected String direction;

    /**
     * Constructor.
     *
     * @param jumpable jumpable ground that is to be jumped
     * @param jumpableLocation jumpableLocation referring to the jumpable ground
     * @param direction direction of jumping action
     */
    public JumpAction(Jumpable jumpable, Location jumpableLocation, String direction) {
        this.jumpable = jumpable;
        this.jumpableLocation = jumpableLocation;
        this.direction = direction;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param gameMap The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap gameMap){
        return this.jumpable.jump(actor, jumpableLocation, gameMap);
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to " + jumpable + " to " + direction;
    }
}
