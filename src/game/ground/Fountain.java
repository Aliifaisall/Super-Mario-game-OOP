package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.action.FillWaterAction;
import game.actor.Drinker;
import game.item.Bottle;

/**
 * Class representing a fountain that is a source of water.
 */
public abstract class Fountain extends Ground {

    /**
     * Constructor.
     *
     * @param displayChar fountain's display char
     */
    public Fountain(char displayChar){
        super(displayChar);
    }

    /**
     * Returns an empty Action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();

        if (location.containsAnActor()) {
            if (actor.hasCapability(Status.DRINK_BOTTLE)){
            actionList.add(new FillWaterAction(this));}
        }


        return actionList;
    }

    /**
     * Filling in the bottle with the water from the fountain.
     *
     * @return description that the bottle has been filled with the water.
     */
    public abstract String fillWater(Bottle bottle);
}
