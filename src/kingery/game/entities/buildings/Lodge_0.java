package kingery.game.entities.buildings;

import kingery.game.engine.Engine;
import kingery.game.gfx.Assets;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class Lodge_0 extends Building {

	public Lodge_0(final Engine e) {
		super(0, 39 * Tile.width, 10 * Tile.width, Assets.BLDG_CABIN, e, Island.Utopia);
		
	}

}
