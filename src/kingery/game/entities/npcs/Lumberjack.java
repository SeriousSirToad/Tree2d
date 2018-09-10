package kingery.game.entities.npcs;

import kingery.game.engine.Engine;
import kingery.game.gfx.Assets;
import kingery.game.islands.Island;

public class Lumberjack extends NPC {

	public static final int RED = 0xFF7F0000;
	public static final int GREEN = 0xFF007F00;
	public static final int BLUE = 0xFF00007F;
	public Lumberjack(int x, int y, String[] stuffs, String name, int type, Engine e, Island island) {
		super(x, y, stuffs, name, Assets.getLumberjack(type), e, island);
	}
	
	public Lumberjack(int x, int y, String stuffs, String name, int type, Engine e, Island island) {
		super(x, y, stuffs, name, false, Assets.getLumberjack(type), e, island);
	}

}
