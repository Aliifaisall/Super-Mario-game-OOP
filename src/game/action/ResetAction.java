package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.ResetManager;

/**
 * Special Action for resetting the game.
 */
public class ResetAction extends Action {

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    public String execute(Actor actor, GameMap map){
        ResetManager resetManager;
        resetManager = ResetManager.getInstance();
        resetManager.run();
        return "The game has been resetted";
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    public String menuDescription(Actor actor){
        return "Player resets the game";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * There's no central management system for this, so you need to be careful not to use the same one twice.
     * See https://en.wikipedia.org/wiki/Connascence
     *
     * @return The key we use for this Action in the menu, or null to have it assigned for you.
     */
    public String hotkey() {
        return "r";
    }
}
