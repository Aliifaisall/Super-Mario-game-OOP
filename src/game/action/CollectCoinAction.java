package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Wallet;
import game.Status;

public class CollectCoinAction extends Action {

    /**
     * Players wallet
     */
    private Wallet wallet = new Wallet();

    /**
     * The Item to be picked up (Coin)
     */
    private Item target;

    /**
     * Constructor.
     *
     * @param target the item to be picked up (Coin)
     */
    public CollectCoinAction(Item target) {
        this.target = target;
    }

    /**
     * This will be performed when _______ (something to do with the tick) is achieved.
     * The coin is added into the inventory of the player, however removed straight away.
     * As the player will only gain $5 when destroying a high ground, I've implemented an
     * 'if' statement to check whether they possess a Power Star (high grounds can only be
     * destroyed upon consuming a Power Star).
     * <p>
     * Else, this will be treated as a normal instance (the coin spawning from the sapling),
     * which adds $20 to their wallet.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(target);
        actor.addItemToInventory(target);
        actor.removeItemFromInventory(target);
        if (actor.hasCapability(Status.CONSUMED_POWERSTAR)) {
            wallet.addMoney(5);
            return actor + " has picked up a coin ! ($5)";
        } else {
            wallet.addMoney(20);
            return actor + " has picked up a coin ! ($20)";
        }
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up a coin";
    }
}



