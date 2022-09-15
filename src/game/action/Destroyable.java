package game.action;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface that is implemented by destroyable grounds (e.g. Wall and Tree)
 */
public interface Destroyable {

    /**
     * Allows any classes that use this interface to implement features when it is destroyed by the Actor (player).
     *
     * @param actor Actor destroying the ground.
     * @param location Location referring to the ground that will be destroyed by the Actor.
     * @return a String indicating that the Actor has destroyed the ground (e.g. Mario consumed Power Star.)
     */
    String destroyed(Actor actor, Location location);

}
