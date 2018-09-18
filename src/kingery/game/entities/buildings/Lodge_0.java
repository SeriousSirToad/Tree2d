package kingery.game.entities.buildings;

import kingery.game.engine.Engine;
import kingery.game.gfx.Assets;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;
import kingery.ui.BuildingWindow;

public class Lodge_0 extends Building {
	
	public Lodge_0(Engine e) {
		super(0, 39 * Tile.width, 10 * Tile.width, Assets.BLDG_SHOPPE_TEST, e, Island.Utopia);
		this.bldg_name = "Lodge";
		this.bldg_desc = "Welcome to the lodge. You want anything?";
		String[] buttons = { "Burger", "Burger", "Burger" };
		interior = new BuildingWindow(buttons, bldg_desc, this);
	}

}
