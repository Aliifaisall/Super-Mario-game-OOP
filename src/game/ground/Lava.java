package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.Player;

/**
 * Class representing a lava ground that damages the player.
 */
public class Lava extends Ground {

    /**
     * Constructor.
     *
     */
    public Lava() {
        super('L');
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        Display display = new Display();
        if (location.containsAnActor()) {
            actor.hurt(15);
            display.print(actor + " has been inflicted by 15\n");
        }
        return new ActionList();
    }

    /**
     * If the actor is the player, return true, otherwise return false.
     *
     * @param actor the Actor to check
     * @return if the actor is the player (Mario), return true, otherwise return false.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor instanceof Player){
            return true;
        }
        return false;
    }
}
