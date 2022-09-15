package game.action;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface that is implemented by consumable items.
 */
public interface Consumable {

    /**
     * Allows any classes that use this interface to implement features when it is consumed by the Actor (player).
     *
     * @param actor Actor consuming the item.
     * @return String indicating that the Actor has consumed the item (e.g. Mario consumed Power Star.)
     */
    String consume(Actor actor);

}