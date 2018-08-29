package kingery.game.engine;

import java.util.Random;

import javax.swing.JOptionPane;

import kingery.game.entities.Player;
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
		p = new Player(JOptionPane.showInputDialog("Please input a name"), 64, 64, engine.input, engine);
		Utopia();
		//40 * Tile.width, 23 * Tile.width
		

	}
	
	public void Utopia() {
		String johnText = 
				  "Hello and welcome to Tree Town pre Alpha release -1.0. \n "
				+ "It's nice here, no? This is the Utopia level. Have fun \n"
				+ "fun developing! Just kidding.";
		NPC npc_1 = new Lumberjack_A(40 * Tile.width, 22 * Tile.width, "Ian", engine, Island.Utopia);
		NPC john = new NPC(11 * Tile.width, 6 * Tile.width, johnText, "John", Assets.NPC_LUMBERJACK_A, engine, Island.Utopia);
		
	}

}
