package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.BottleManager;
import game.actor.Drinker;
import game.ground.Fountain;
import game.item.Bottle;

/**
 * Special Action for filling in the bottle of the player.
 */
public class FillWaterAction extends Action {

    /**
     * Fountain that is the source of the water
     */
    private Fountain fountain;

    /**
     * Constructor.
     *
     * @param fountain fountain that is the source of the water
     */
    public FillWaterAction(Fountain fountain){
        this.fountain = fountain;
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
        Bottle bottle = BottleManager.getInstance().getBottle(actor);
        return fountain.fillWater(bottle);
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fills the bottle with " + fountain;
    }
}
