package kingery.game.entities.npcs;

import kingery.game.gfx.Assets;
import kingery.game.islands.Island;

public class Lumberjack extends NPC {

	public static final int RED = 0xFF7F0000;
	public static final int GREEN = 0xFF003F00;
	public static final int BLUE = 0xFF00007F;
	public static final int PURPLE = 0xFF7F007F;
	public static final int LUMBERJACK_FRIEND = 0;
	public static final int LUMBERJACK_NUETRAL = 1;
	public static final int LUMBERJACK_VENDOR = 2;
	public static final int LUMBERJACK_ENEMY = 3;

	public Lumberjack(int x, int y, String[] stuffs, String name, int type, Island island) {
		super(x, y, stuffs, name, Assets.getLumberjack(type), island);
	}

	public Lumberjack(int x, int y, String stuffs, String name, int type, Island island) {
		super(x, y, stuffs, name, false, Assets.getLumberjack(type), island);
	}

}
