package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;


/**
 * a class that allows the player to destroy the shells of Koopa's and Flying Koopa's when in their dormant states.
 *
 * - Madeline Ting
 */

public class DestroyShellAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public DestroyShellAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executed when the player uses this class method.
     * This makes sure to drop Goomba's held item in their inventory (super mushroom)
     * and removes themselves from the map.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        if (target.hasCapability(Status.KOOPA_DORMANT)) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            map.removeActor(target);
            return "Koopa's shell has been destroyed. Oh look, it's a Super Mushroom !";
        }

        return null;
    }


        @Override
        public String menuDescription(Actor actor) {
            return actor + " breaks " + target + "'s shell at " + direction;
        }
    }