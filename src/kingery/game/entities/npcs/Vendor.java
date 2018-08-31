package kingery.game.entities.npcs;

import kingery.game.engine.Engine;
import kingery.game.gfx.Assets;
import kingery.game.inventory.Inventory;
import kingery.game.islands.Island;

public class Vendor extends NPC{
	Inventory inventory; 
	
	public Vendor (int x, int y, String name, boolean inverted, Engine e, Island island) {
		super(x, y, "Want to trade?", name, true, Assets.NPC_LUMBERJACK_A, inverted, e, island);
		inventory = new Inventory(640, 0, 225, 225, e);
	}

}


