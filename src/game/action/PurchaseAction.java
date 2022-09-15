package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;

public class PurchaseAction extends Action {

    protected Item item;

    protected int value;

    protected Actor target;

    protected Wallet wallet = new Wallet();





    public PurchaseAction(Actor target, Item item, int value){
        this.target = target;
        this.item = item;
        this.value = value;

    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item + " ($" + value + ")";
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        if (wallet.canAfford(value)){

            wallet.removeMoney(value);
            target.addItemToInventory(item);
            return "You have bought the " + item + " ! Hooray !";

        }
        return "You don't have enough coins!";
    }


}
