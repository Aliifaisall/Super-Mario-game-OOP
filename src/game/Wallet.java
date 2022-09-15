package game;

import edu.monash.fit2099.engine.actions.Action;

public class Wallet {

    private int wallet = 0; //this is the cash money

    public void addMoney(int value) {
        wallet += value;
    }

    public void removeMoney(int value) {
        wallet -= value;
    }

    public boolean canAfford(int value) {
        return wallet - value > 0;
    }
}
