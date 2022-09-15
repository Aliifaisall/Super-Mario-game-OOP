package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Special Action for the Actor (player) to consume the consumable item.
 */
public class DestroyAction extends Action {

    /**
     * The ground that is to be destroyed
     */
    private Destroyable destroyable;

    /**
     * The location of the ground that is to be destroyed
     */
    private Location location;

    /**
     * The direction of destroying action
     */
    private String direction;

    /**
     * Constructor.
     *
     * @param destroyable destroyable that is to be destroyed
     * @param location location of the ground that is to be destroyedon
     * @param direction direction of destroying action
     */
    public DestroyAction(Destroyable destroyable, Location location, String direction){
        this.destroyable = destroyable;
        this.location = location;
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
        return destroyable.destroyed(actor, location);
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroys " + destroyable + " at " + direction;
    }
}
