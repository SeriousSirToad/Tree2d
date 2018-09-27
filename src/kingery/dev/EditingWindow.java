package kingery.dev;

import java.awt.Graphics2D;

import kingery.game.engine.Engine;
import kingery.game.engine.InputHandler;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;
import kingery.ui.GameButton;
import kingery.ui.GameWindow;

public class EditingWindow {

	GameWindow gw;
	GameWindow warp;

	public EditingWindow() {

		gw = new GameWindow() {
			public void update(Graphics2D g) {
				InputHandler input = Engine.p.input;
				if (input.r.isPressed()) {
					active = true;
				}
				if (active) {
					show();
				}
			}
		};
		warp = new GameWindow();

		gw.buttons = new GameButton[] {

				new GameButton(1 * Tile.scale, 1 * Tile.scale, 0xFF007F00,
						"Warp") {
					public void onClick() {
						warp.active = true;
						gw.active = false;
					}
				},

				new GameButton(2 * Tile.scale + GameButton.STD_WIDTH,
						1 * Tile.scale, 0xFF007F00, "Change time") {
					public void onClick() {
						Engine.island.time++;
					}
				}

		};

		warp.buttons = new GameButton[] {

		new GameButton(1 * Tile.scale, 1 * Tile.scale, 0xFF420dab, "Lodge") {
			public void onClick() {
				Engine.p.changeIsland(Island.Utopia);
				Engine.p.x = 39 * Tile.width;
				Engine.p.y = 17 * Tile.width;
			}
		}

		};

	}
}
