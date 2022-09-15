package game.ground;



public class Wall extends HighGround {
	private double successRate = 0.8;
	private int damage = 20;

	public Wall() {
		super('#');
	}


	@Override
	public boolean blocksThrownObjects() {
		return true;
	}


	public double getSuccessRate(){return successRate;};

	public int getDamage(){return damage;};

	@Override
	public String toString(){return "Wall";};

}
