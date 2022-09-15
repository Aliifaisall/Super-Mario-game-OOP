package game.actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.action.Resettable;
import game.action.ResetAction;

import game.Status;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable, Drinker{

	private final Menu menu = new Menu();
	private int wallet = 0;
	private int damage = 5;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(game.Status.HOSTILE_TO_ENEMY);
		this.registerInstance();
		this.addCapability(Status.DRINK_BOTTLE);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (!this.hasCapability(Status.RESETCOMPLETE) && (!this.hasCapability(Status.RESETTED))){
			actions.add(new ResetAction());
		}
		if (this.hasCapability(Status.RESETTED)) {
			this.heal(this.getMaxHp());
			this.removeCapability(Status.RESETTED);
			this.addCapability(Status.RESETCOMPLETE);
		}

		//For the end of the game
		if (this.hasCapability(Status.END_GAME)) {
			actions.clear();
			actions.add(new ResetAction());
		}

		// Applies this status to the player when their
		if (this.getInventory().toString().contains("Wrench")) {
			this.addCapability(Status.WRENCH_DESTROYABLE);
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}


	@Override
	public char getDisplayChar() {
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()) : super.getDisplayChar();
	}

	@Override
	public void hurt(int point) {
		if (this.hasCapability(Status.TALL)) {
			this.removeCapability(Status.TALL);
		} else if (this.hasCapability(Status.CONSUMED_POWERSTAR)){
			super.hurt(0);}
		else{ super.hurt(point);}
	}

	@Override
	public void heal(int point){
		super.heal(point);
	}

	@Override
	public void resetInstance(){
		this.addCapability(Status.RESETTED);
		}

	public void addMoney(int value) {
		wallet += value;
	}

	public void removeMoney(int value) {
		wallet -= value;
	}

	public boolean canAfford(int value) {
		return wallet - value > 0;
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * By default, the Actor 'punches' for 5 damage. Override this method to create
	 * an Actor with more interesting descriptions and/or different damage.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(damage, "punches");
	}

	public String toString(){
		return name;
	}

	@Override
	public void increaseAttack(int point){
		this.damage += point;
	}


}
