package game.item;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.action.CollectCoinAction;
import game.Status;


/**
 *
 *
 * A coin class for coins that will spawn on the map.
 *
 * ($20 added for coins picked up from the sapling, $5 added from destroyed higher grounds).
 *
 */

public class Coin extends Item {

    /***
     * Constructor.
     */
    public Coin() {
        super("coin", '$', true);
    }

    /**
     * When the player interacts with the coin on the map.
     * CollectCoinAction will be acquired by the player; which allows the player to pick up the coin.
     * Consequently, the value of the coin will be added to the player's wallet.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new CollectCoinAction(this));
        }
        return actions;
    }

    /**
     * Inform a carried Item of the passage of time. why am i doing this i don't think this is needed... helP
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
  /**  @Override
    public void tick(Location currentLocation, Actor actor, ActionList actions) {
        ActionList actions = new ActionList();
        if (actor.getInventory().contains(this)){
            this.tick(currentLocation, actor);
        }
    } **/


    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     */
    public void tick(Location currentLocation, GameMap map) {
        if (this.hasCapability(Status.RESETTED)) {
            currentLocation.removeItem(this);
            this.removeCapability(Status.RESETTED);
            this.addCapability(Status.RESETCOMPLETE);
        }
    }


}
