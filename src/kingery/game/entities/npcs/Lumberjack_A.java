package kingery.game.entities.npcs;

import kingery.game.engine.Engine;
import kingery.game.gfx.Assets;
import kingery.game.islands.Island;

public class Lumberjack_A extends NPC {

	public Lumberjack_A(int x, int y, String name, boolean inverted, Engine e, Island island) {
		super(x, y, "Hiya there", name, false, Assets.NPC_LUMBERJACK_A, inverted, e, island);
	}

}
