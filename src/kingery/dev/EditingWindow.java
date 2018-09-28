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
	boolean canOpen = false;

	public EditingWindow() {
		warp = new GameWindow();
		gw = new GameWindow() {
			public void update(Graphics2D g) {
				InputHandler input = Engine.p.input;

				if (!input.r.isPressed()) {
					canOpen = true;
				}

				if (canOpen && !Engine.inMenu.inMenu) {
					if (!active) {
						if (input.r.isPressed()) {
							active = true;
							canOpen = false;
						}
					} else {
						if (input.r.isPressed()) {
							warp.active = false;
							active = false;
							canOpen = false;
						}
					}
				}

				if (active && !warp.active) {
					show();
				}
			}

		};

		gw.buttons = new GameButton[] {

				new GameButton(1 * Tile.scale, 1 * Tile.scale, 0xFF007F00,
						"Warp") {
					public void onClick() {
						warp.active = true;
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
