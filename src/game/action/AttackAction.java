package game.action;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.item.Fire;


/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {
	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor.hasCapability(Status.CONSUMED_POWERSTAR)) {
			target.resetMaxHp(0);
			return "target is killed by the player";}
		else { Weapon weapon = actor.getWeapon();
		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";


		// fire attack

		if (actor.hasCapability(Status.FIRE_ATTACK)){
			Fire fire = new Fire();
			Location location = map.locationOf(target);
			location.addItem(fire);
		}

		target.hurt(damage);
		if (!target.isConscious()) {
			ActionList dropActions = new ActionList();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// when koopa enters its dormant state, rather than being killed
			if (target.hasCapability(Status.KOOPA_ACTIVE)) {
				target.removeCapability(Status.KOOPA_ACTIVE);
				target.addCapability(Status.KOOPA_DORMANT);
				if (target.hasCapability(Status.KOOPA_FLYING)){
					target.removeCapability(Status.KOOPA_FLYING);
				}
				result += System.lineSeparator() + target + " is now dormant !";
				return result;
			}
			//removing the actor from the map
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
		} return result;
		}
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}

