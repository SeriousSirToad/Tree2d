package kingery.dev;

import java.awt.Graphics2D;

import kingery.game.engine.Engine;
import kingery.game.engine.GameState;
import kingery.game.engine.InputHandler;
import kingery.game.gfx.lighting.BigLight;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;
import kingery.game.menu.InGameMenu;
import kingery.ui.Organizer;
import kingery.ui.component.GameButton;
import kingery.ui.component.GameWindow;

public class EditingWindow {

	GameWindow gw;
	GameWindow warp;
	GameWindow time;
	boolean canOpen = false;

	public EditingWindow() {
		warp = new GameWindow();
		gw = new GameWindow() {
			public void update(Graphics2D g) {
				InputHandler input = Engine.p.input;

				if (!input.r.isPressed()) {
					canOpen = true;
				}

				if (canOpen) {
					if (!active && !InGameMenu.inMenu) {
						if (input.r.isPressed()) {
							active = true;
							canOpen = false;
						}
					} else {
						if (input.r.isPressed() || InGameMenu.inMenu) {
							warp.active = false;
							time.active = false;
							active = false;
							canOpen = false;
						}
					}
				}

				if (active && !warp.active && !time.active) {
					show(g);
				}
			}

		};

		gw.buttons = new GameButton[] {

				new GameButton(1 * Tile.scale, 1 * Tile.scale, 0xFF007F00, "Warp") {
					public void onClick() {
						warp.active = true;
					}
				},

				new GameButton(2 * Tile.scale + GameButton.STD_WIDTH, 1 * Tile.scale, 0xFF007F00, "Change time") {
					public void onClick() {
						time.active = true;
					}
				}

		};

		warp.buttons = new GameButton[] {

				new GameButton(1, 1, 0xFF420dab, "Lodge") {
					public void onClick() {
						Engine.p.changeIsland(Island.Utopia);
						Engine.p.x = 40 * Tile.width;
						Engine.p.y = 18 * Tile.width;
					}
				}

		};

		time = new GameWindow();
		time.buttons = new GameButton[] {

				new GameButton(0, 0, 0xFF4F4F4F, "Dark") {
					public void onClick() {
						GameState.time = 0;
					}
				}, new GameButton(0, 0, 0xFF7F7F7F, "Not dark") {
					public void onClick() {
						GameState.time = 12;
					}
				}, new GameButton(0, 0, BigLight.evening.getRGB(), "Sunset") {
					public void onClick() {
						GameState.time = 18;
					}
				}

		};

		Organizer.organizeLeft(time.buttons);

	}
}
