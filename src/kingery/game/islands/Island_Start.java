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
		lights.add(new Light(25, 7, Tile.width * 3, new Color(0, 0, 0, 0)));
		lights.add(new Light(25, 10, Tile.width * 3, new Color(66, 13, 171, 100)));
		lights.add(new BigLight(this));
		rightI = Island.Utopia;
	}

}
