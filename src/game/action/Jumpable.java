package game.action;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface that is implemented by the Actor jumping
 */
public interface Jumpable {

    /**
     * Allows any classes that use this interface to implement features when it is jumped by the Actor (player).
     *
     * @param actor Actor jumping the ground.
     * @param location Location referring to the ground that will be jumped by the Actor.
     * @param gameMap gameMap the actor is on.
     * @return a String indicating that the Actor has jumped the ground (e.g. Mario jumped to the Tree successfully! (39, 11))
     *
     */
    String jump(Actor actor, Location location, GameMap gameMap);


}