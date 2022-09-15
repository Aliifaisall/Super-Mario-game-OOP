package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actor.Bowser;
import game.actor.Player;
import game.actor.PrincessPeach;
import game.actor.Toad;
import game.ground.*;
import game.item.Bottle;
import game.item.PowerStar;
import game.item.SuperMushroom;
import game.managers.BottleManager;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {
			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			GameMap lavaMap = new GameMap(groundFactory, '.', 40, 10);
			world.addGameMap(lavaMap);

			WarpPipe lavaPipe = new WarpPipe(null, null);
			lavaMap.at(0, 0).setGround(lavaPipe);

			gameMap.at(0, 0).setGround(new WarpPipe(lavaMap.at(0, 0), lavaPipe));
			gameMap.at(20, 13).setGround(new WarpPipe(lavaMap.at(0, 0), lavaPipe));
			gameMap.at(40, 5).setGround(new WarpPipe(lavaMap.at(0, 0), lavaPipe));

			lavaMap.at(10, 9).setGround(new Lava());
			lavaMap.at(12, 5).setGround(new Lava());
			lavaMap.at(30, 3).setGround(new Lava());


			Player mario = new Player("Mario", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));

			Bottle bottle = new Bottle(mario);
			mario.addItemToInventory(bottle);
			BottleManager.getInstance().appendBottleInstance(mario, bottle);


			gameMap.locationOf(mario).addItem(new PowerStar());
			gameMap.locationOf(mario).addItem(new SuperMushroom());

			gameMap.at(44, 11).setGround(new HealthFountain());
			gameMap.at(45, 10).setGround(new PowerFountain());


			// toad here
			Toad toad = new Toad();
			gameMap.addActor(toad, gameMap.at(42,11));


			// princess peach here
		    PrincessPeach princessPeach = new PrincessPeach();
			lavaMap.addActor(princessPeach , lavaMap.at(24, 5));

			//Bowser here
			Bowser bowser = new Bowser();
			lavaMap.addActor(bowser , lavaMap.at(25, 5));

			



		world.run();

	}
}
