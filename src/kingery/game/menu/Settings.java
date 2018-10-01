package kingery.game.menu;

import kingery.game.engine.Engine;
import kingery.game.islands.tiles.Tile;
import kingery.ui.GameButton;
import kingery.ui.GameWindow;

public class Settings {

	public static int frameCap = 60;
	public static int musicVolume = 0;
	public static int fxVolume = 0;

	public static GameButton[] buttons;
	public static GameWindow window;

	public static void init() {

		buttons = new GameButton[] {

		new GameButton(0, 0, 0xFF3F3F3F, "60") {
			public void onClick() {
				frameCap = 60;
			}
		},

		new GameButton(0, 0, 0xFF3F3F3F, "Infinite") {
			public void onClick() {
				frameCap = 0;
			}
		},

		new GameButton(0, 0, 0xFF7F0000, "Close") {
			public void onClick() {
				window.active = false;
			}
		}

		};

		for (int i = 0; i < buttons.length; i++) {
			organize(buttons);
		}

		window = new GameWindow();
		window.buttons = buttons;

	}

	public static void organize(GameButton[] buttons) {
		for (int i = 0; i < buttons.length; i++) {
			GameButton b = buttons[i];
			if (i > 0) {
				GameButton last = buttons[i - 1];
				if (!(last.x + last.width > Engine.WIDTH - b.width)) {
					b.x = last.x + last.width + 1 * Tile.scale;
					b.y = Tile.scale;
				}
			} else {
				b.x = Tile.scale;
				b.y = Tile.scale;
			}
		}
	}
}
