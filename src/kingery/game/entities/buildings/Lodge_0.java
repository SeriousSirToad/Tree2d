package kingery.game.entities.buildings;

import kingery.game.gfx.Assets;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class Lodge_0 extends Building {

	public Lodge_0(int x, int y, Island island) {
		super(x * Tile.width, y * Tile.width, Assets.BLDG_CABIN, island);
	}

}
