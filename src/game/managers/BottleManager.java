package game.managers;
import edu.monash.fit2099.engine.actors.Actor;
import game.item.Bottle;

import java.util.HashMap;

/**
 * A global Singleton manager that does keep all the actor's bottle.
 */
public class BottleManager {

    /**
     * A list of bottle instances
     *
     */
    private HashMap<Actor, Bottle> bottleList;

    /**
     * A singleton bottle manager instance
     */
    private static BottleManager instance;

    /**
     * Constructor
     */
    private BottleManager(){
        bottleList = new HashMap<Actor, Bottle>();
    }

    /**
     * Get the singleton instance of bottle manager
     * @return BottleManager singleton instance
     */
    public static BottleManager getInstance(){
        if(instance == null){
            instance = new BottleManager();
        }
        return instance;
    }

    /**
     * returns the actor's bottle.
     *
     * @param actor the bottle's keeper
     * @return the actor's bottle
     */
    public Bottle getBottle(Actor actor){
        return bottleList.get(actor);
    }

    /**
     * Add the bottle instance to the bottlelist (hash map)
     *
     * @param actor the bottle's keeper
     * @param bottle the actor's bottle
     */
    public void appendBottleInstance(Actor actor, Bottle bottle){
        this.bottleList.put(actor, bottle);
    }


}
