package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologues;

/**
 * a Class that represent the speaking action that an actor can perform
 * @Author: Ali Maskari
 * @version 4.2.0
 */

public  class SpeakAction extends Action {
    /**
     * an instance of the class monologue
     */
    private Monologues monologue;

    /**
     * create an instance of the Speakable interface to be used [ speaker attribute of type Speakable]
     */

    private Speakable speaker;



    /**
     * Constructor that has speaker and monologue as their attributes
     * @param speaker
     * @param monologue
     */


    public SpeakAction(Speakable speaker, Monologues monologue) {
        this.monologue = monologue;
        this.speaker = speaker;
    }

    /**
     * Perform the Action of speaking
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return speaker.speak();
    }



     /**
      * Menu description of the talking action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks to " + speaker;
    }





}
