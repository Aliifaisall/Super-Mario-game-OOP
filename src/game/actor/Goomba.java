package game.actor;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologues;
import game.action.Speakable;
import game.ground.Dirt;

import java.util.Random;


/**
 * A little fungus guy.
 */
public class Goomba extends Enemy implements Speakable {
	private final Monologues monologue = new Monologues();
	private int turn = 0;
	private Random rand = new Random();

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 50);

		//speak
		monologue.pushMonologue(0,"Mugga mugga!");
		monologue.pushMonologue(1,"Ugha ugha... (Never gonna run around and desert you...)");
		monologue.pushMonologue(2,"Ooga-Chaka Ooga-Ooga!");

	}


	/**
	 * Figure out what to do next.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (turn % 2 == 0){

			display.println(speak());
		}


		turn += 1;
		actions.clear();

		return super.playTurn(actions, lastAction, map, display);
	}



	public void execute(Location location) {
		boolean val = rand.nextInt(100) < 10;
		if (val){
			Dirt dirt = new Dirt();
			location.setGround(dirt);
			}
		if (!this.isConscious()){
			Dirt dirt = new Dirt();
			location.setGround(dirt);
		}

	}


	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "kicks");
	}


	@Override
	public void resetInstance() {

	}

	@Override
	public String speak() {
		return this + ": " + monologue.getMonologue();
	}
	public String toString(){
		return "Goomba";
	}

}
