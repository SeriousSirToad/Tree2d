package kingery.ui;

import kingery.game.engine.Engine;
import kingery.game.islands.tiles.Tile;

public class Organizer {

	public static void organizeLeft(GameButton[] buttons) {
		for (int i = 0; i < buttons.length; i++) {
			GameButton b = buttons[i];
			if (i > 0) {
				GameButton last = buttons[i - 1];
				if (!(last.y + last.height > Engine.HEIGHT - b.height)) {
					b.y = last.y + last.height + 1 * Tile.scale;
					b.x = Tile.scale;
				}
			} else {
				b.x = Tile.scale;
				b.y = Tile.scale;
			}
		}
	}

	public static void organizeLeft(GameButton[][] buttons) {

		for (int i = 0; i < buttons.length; i++) {
			GameButton b = buttons[0][i];
			if (i > 0) {
				GameButton last = buttons[0][i - 1];
				if (!(last.y + last.height > Engine.HEIGHT - b.height)) {
					b.y = last.y + last.height + 1 * Tile.scale;
					b.x = Tile.scale;
				}
			} else {
				b.x = Tile.scale;
				b.y = Tile.scale;
			}

		}

	}

}
