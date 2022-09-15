package game.ground;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.action.Destroyable;
import game.action.Jumpable;
import game.Status;
import game.action.DestroyAction;
import game.action.JumpAction;
import game.item.Coin;

/**
 * Class representing terrain type of HighGround (e.g. Wall and Tree)
 */
public abstract class HighGround extends Ground implements Jumpable, Destroyable {

    /**
     * the Success Rate indicating how successful the jump will be
     */
    protected double successRate;

    /**
     * the damage that the Actor will get if the jump is unsuccessful
     */
    protected int damage;

    /**
     * the name of the high ground (e.g. Tree and Wall)
     */
    protected String name;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HighGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Returns an empty Action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();

        if (!location.containsAnActor()) {
            if (actor.hasCapability(game.Status.CONSUMED_POWERSTAR)) {
                actionList.add(new DestroyAction(this, location, direction));
            } else {
                actionList.add(new JumpAction(this, location, direction));
            }
        }

        return actionList;
    }

    /**
     * Destroyed the Ground by the Actor
     *
     * @param actor Actor destroying the ground.
     * @param location Location referring to the ground that will be destroyed by the Actor.
     * @return a String indicating that the Actor has destroyed the ground (e.g. Mario consumed Power Star.)
     */
    @Override
    public String destroyed(Actor actor, Location location){
        location.setGround(new Dirt());

        Coin coin = new Coin();
        location.addItem(new Coin());
        return "High ground has been successfully broken by " + actor;
    }

    /**
     * the Actor jumps the High Ground
     *
     * @param actor Actor jumping the ground.
     * @param location Location referring to the ground that will be jumped by the Actor.
     * @param gameMap gameMap the actor is on.
     * @return a String indicating that the Actor has jumped the ground (e.g. Mario jumped to the Tree successfully! (39, 11))
     */
    @Override
    public String jump(Actor actor, Location location, GameMap gameMap) {
        if ((Math.random() <= this.getSuccessRate()) || (actor.hasCapability(Status.TALL))) {
            gameMap.moveActor(actor, location);
            return actor + " jumped to the " + this + " successfully! (" + location.x() + ", " + location.y() + ")";
        } else {
            actor.hurt(this.getDamage());
            return actor + " failed to jump to the " + this + " with the damage of " + getDamage() + " (" + location.x() + ", " + location.y() + ")";
        }
    }

    /**
     * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
     *
     * @param actor the Actor to check
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.KOOPA_FLYING)) {
            return true;
        }
        else return false;
    }

    /**
     * getter for success rate
     *
     * @return successRate indicating how successful the jump will be
     */
    public double getSuccessRate(){return successRate;};

    /**
     * getter for damage
     *
     * @return damage that the Actor will get if the jump is unsuccessful
     */
    public int getDamage(){return damage;};


}
