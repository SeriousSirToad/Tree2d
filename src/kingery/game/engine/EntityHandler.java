package kingery.game.engine;

import java.util.Random;

import kingery.game.entities.Player;
import kingery.game.entities.buildings.Building;
import kingery.game.entities.npcs.Lumberjack_A;
import kingery.game.entities.npcs.NPC;
import kingery.game.entities.npcs.Vendor;
import kingery.game.gfx.Assets;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class EntityHandler {

	Engine engine;

	public Player p;

	public Random r = new Random();

	public EntityHandler(Engine engine) {

		this.engine = engine;
		p = new Player("Developer", 1 * Tile.width, 0 * Tile.width, engine.input, engine, engine.island);
		Utopia();
		Island_1();
		// 40 * Tile.width, 23 * Tile.width

	}

	@SuppressWarnings("unused")
	public void Utopia() {
		
		Building bldg_0 = new Building(0, 39 * Tile.width, 10 * Tile.width, Assets.BLDG_SHOPPE_TEST, engine, Island.Utopia);
		
		String johnText = "Hello " + p.name + " and welcome to Tree Town pre Alpha release \n "
				+ "0.0. It's nice here, no? This is the Utopia level. Have fun \n" + "developing! Just kidding.";
		String hermitText = "Hmmm, very good indeed. Bes bes, mmm bes.";
		String vendorText = "Want to trade?";
		String[] parque = { "I really love the park.", "Did you know I am the most recent addition \n" + "to this map?",
				"You're very chatty." };
		NPC npc_1 = new Lumberjack_A(40 * Tile.width, 22 * Tile.width, "Ian", false, engine, Island.Utopia);
		NPC npc_2 = new NPC(54 * Tile.width, 32 * Tile.width, parque, "Park guy", Assets.NPC_LUMBERJACK_B, false,
				engine, Island.Utopia);
		NPC john = new NPC(11 * Tile.width, 6 * Tile.width, johnText, "John", false, Assets.NPC_LUMBERJACK_A, true,
				engine, Island.Utopia);
		NPC hermit = new NPC(5 * Tile.width, 97 * Tile.width, hermitText, "Hermit", false, Assets.NPC_HERMIT, true,
				engine, Island.Utopia);
		NPC vendor = new Vendor(20 * Tile.width, 40 * Tile.width, "Bip pippa", true, engine, Island.Utopia);

	}

	@SuppressWarnings("unused")
	public void Island_1() {

		String[] josephText = {"What? Oh, Sorry. Hey.", "Don't you think it's strange there's only 2 islands?"};
		NPC npc_1 = new NPC(7 * Tile.width, 7 * Tile.width, josephText, "Joseph", Assets.NPC_LUMBERJACK_B, false,
				engine, Island.Test);
		// Building shoppe = new Building(20 * Tile.width, 17 * Tile.width, false,
		// Assets.BLDG_SHOPPE_TEST, engine, Island.Test);

	}

}
