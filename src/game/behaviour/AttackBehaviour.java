package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.action.AttackAction;
import game.Status;

/**
 * A class that allows the enemy to attack the player when in its attacking range
 */

public class AttackBehaviour extends Action implements Behaviour {
    public AttackBehaviour() {
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(actor))
            return null;

        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && actor.toString().contains("Mario")) {
                return new AttackAction(destination.getActor(), exit.getName());
            }
        }
        return null;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Raagrh...";
    }
}



