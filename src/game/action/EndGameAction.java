package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * a class created for ending the game (initiated by interacting with Princess Peach)
 */

public class EndGameAction extends Action  {

    /**
     * Prints the end game message in the console and stops mario from progressing in the game further, marking the
     * end of the game.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getInventory().toString().contains("Key")) {
            actor.addCapability(Status.END_GAME);
            return actor + "! You saved me! Thank you so much, your bravery is much appreciated <3\n" +
                    "However... I was really prepared to deal with Bowser on my own, and kick him! >:)\n" +
                    "Anyway, you must be tired, how about we go home ?\n" +
                    "Besides, I've baked a cake for you, shall we return and try it together ? <3\n" +
                    "*** Game Over ***";


        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " releases Princess Peach from the chains.";
    }

    /**
     * if the player Enters the String k , the EndGameAction will commence
     * @return the String "k"
     */

    @Override
    public String hotkey() {
        return "k";
    }


    }


