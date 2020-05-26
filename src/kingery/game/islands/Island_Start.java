package kingery.game.islands;

import kingery.game.entities.npcs.NPC;
import kingery.game.gfx.Assets;

public class Island_Start extends Island {

	public Island_Start() {
		super("res/Islands/test.png");
		new NPC(5, 5, "Hi", "", false, Assets.LUMBERJACK1, this);
	}

	public void init() {
		
	}

}
