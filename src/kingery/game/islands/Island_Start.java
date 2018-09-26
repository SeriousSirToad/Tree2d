package kingery.game.islands;

import java.awt.Color;

import kingery.game.engine.Engine;
import kingery.game.gfx.lighting.BigLight;
import kingery.game.gfx.lighting.Light;
import kingery.game.islands.tiles.Tile;

public class Island_Start extends Island {

	public Island_Start(Engine e) {
		super("res/Islands/test.png", e);
	}
	
	public void init() {
		rightI = Island.Utopia;
	}

}
