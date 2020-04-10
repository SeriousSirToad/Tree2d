package kingery.game.engine;

import java.util.Random;

import kingery.game.entities.Player;
import kingery.game.entities.buildings.Building;
import kingery.game.entities.buildings.Lodge_0;
import kingery.game.entities.flora.Tree;
import kingery.game.entities.flora.Tree.GrowthState;
import kingery.game.entities.npcs.Eian;
import kingery.game.entities.npcs.Lumberjack;
import kingery.game.entities.npcs.NPC;
import kingery.game.gfx.Assets;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class GameState {

	static Engine engine;

	public static Player p;

	public Random r = new Random();
	
	public static Island currentLevel = Island.Start;

	public static int time = 0;
	
	public static void init(Engine engine) {

		p = new Player("Developer", Tile.width, Tile.width, engine.input, engine, currentLevel);
		
		// 40 * Tile.width, 23 * Tile.width

	}

	@SuppressWarnings("unused")
	public void Utopia() {

		// Building bldg_0 = new Building(0, 39 * Tile.width, 10 * Tile.width,
				// Assets.BLDG_SHOPPE_TEST, engine, Island.Utopia);

				String johnText = "Hello " + p.name + " and welcome to Tree Town pre Alpha release \n "
						+ "0.0. It's nice here, no? This is the Utopia level. Have fun \n" + "developing! Just kidding.";
				String hermitText = "Hmmm, very good indeed. Bes bes, mmm bes.";
				String vendorText = "Want to trade?";
				String[] parque = { "I really love the park.", "Did you know I am the most recent addition \n" + "to this map?",
						"You're very chatty." };
				new Lumberjack(54, 32, parque, "Park guy", 0, engine, Island.Utopia);
				NPC john = new Lumberjack(11, 6, johnText, "John", 0, engine, Island.Utopia);
				NPC hermit = new NPC(5, 97, hermitText, "Hermit", true, Assets.NPC_HERMIT, engine, Island.Utopia);

				new Eian(engine);

				Building lodge = new Lodge_0(engine);

				Tree tree = new Tree(3, 3, GrowthState.GROWN, engine, Island.Utopia);

	}

	@SuppressWarnings("unused")
	public void Island_1() {

		String[] josephText = { "What? Oh, Sorry. Hey.", "Don't you think it's strange there's only 2 islands?" };
		NPC npc_1 = new Lumberjack(7 * Tile.width, 7 * Tile.width, josephText, "Joseph", 1, engine, Island.Start);

	}

}
