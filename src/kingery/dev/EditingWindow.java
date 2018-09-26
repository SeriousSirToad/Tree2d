package kingery.dev;

import kingery.game.engine.Engine;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;
import kingery.ui.GameButton;
import kingery.ui.GameWindow;

public class EditingWindow {

	public EditingWindow() {

		GameWindow gw = new GameWindow();
		
		gw.buttons = new GameButton[] {
				
				new GameButton(1 * Tile.scale, 1 * Tile.scale, 0xFF007F00, "Teleport") {
					public void onClick() {
						Engine.island = Island.Utopia;
						Engine.p.changeIsland(Engine.island);
						Engine.p.x = 39 * Tile.width;
						Engine.p.y = 17 * Tile.width;
					}
				}
				
		};
		
		
	}

}
