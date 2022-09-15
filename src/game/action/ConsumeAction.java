package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special Action for the Actor (player) to consume the consumable item.
 */
public class ConsumeAction extends Action {

    /**
     * the Item that is to be consumed
     */
    private Consumable consumable;

    /**
     * Constructor.
     *
     * @param consumable the Consumable Item that is to be consumed
     */
    public ConsumeAction(Consumable consumable){
        this.consumable = consumable;
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
        return consumable.consume(actor);
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumable;
    }
}
