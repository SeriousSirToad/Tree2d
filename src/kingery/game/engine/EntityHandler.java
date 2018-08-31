package kingery.game.engine;

import java.util.Random;

import javax.swing.JOptionPane;

import kingery.game.entities.Player;
import kingery.game.entities.buildings.Building;
import kingery.game.entities.npcs.Lumberjack_A;
import kingery.game.entities.npcs.NPC;
import kingery.game.gfx.Assets;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class EntityHandler {

	Engine engine;

	public Player p;

	public Random r = new Random();

	public EntityHandler(Engine engine) {

		this.engine = engine;
		p = new Player("John", 1 * Tile.width, 0 * Tile.width, engine.input, engine, engine.island);
		Utopia();
		Island_1();
		//40 * Tile.width, 23 * Tile.width
		

	}
	
	public void Utopia() {
		String johnText = 
				  "Hello " + p.name + " and welcome to Tree Town pre Alpha release \n "
				+ "0.0. It's nice here, no? This is the Utopia level. Have fun \n"
				+ "fun developing! Just kidding.";
		String hermitText =
				  "Hmmm, very good indeed. Bes bes, mmm bes.";
		String vendorText =
				"Want to trade?";
		NPC npc_1 = new Lumberjack_A(40 * Tile.width, 22 * Tile.width, "Ian", false, engine, Island.Utopia);
		NPC john = new NPC(11 * Tile.width, 6 * Tile.width, johnText, "John", false, Assets.NPC_LUMBERJACK_A, true, engine, Island.Utopia);
		NPC hermit = new NPC(5 * Tile.width, 97 * Tile.width, hermitText, "Hermit", false, Assets.NPC_HERMIT, true, engine, Island.Utopia);
		NPC vendor = new NPC(20 * Tile.width, 40 * Tile.width, vendorText, "Vendor", true, Assets.NPC_LUMBERJACK_B, true, engine, Island.Utopia);
		
	}
	
	public void Island_1() {
		
		String josephText = "What? Oh, Sorry. Hey.";
		NPC npc_1 = new NPC(7 * Tile.width, 7 * Tile.width, josephText, "Joseph", false, Assets.NPC_LUMBERJACK_B, false, engine, Island.Test);
		//Building shoppe = new Building(20 * Tile.width, 17 * Tile.width, false, Assets.BLDG_SHOPPE_TEST, engine, Island.Test);
		
	}

}
