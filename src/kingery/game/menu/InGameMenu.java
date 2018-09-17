package kingery.game.menu;

import java.awt.Color;
import java.awt.Graphics;

import kingery.game.engine.Engine;
import kingery.game.islands.tiles.Tile;
import kingery.ui.GameButton;

public class InGameMenu {

	public boolean inMenu = false;
	public Engine e;
	GameButton[] buttons = new GameButton[2];
	public boolean canExitMenu = false;

	public InGameMenu(Engine engine) {
		e = engine;
		buttons[0] = new GameButton(Engine.WIDTH - (17 * Tile.scale),
				1 * Tile.scale, 0xFF9F0000, "Exit", e);
		buttons[1] = new GameButton(Engine.WIDTH - (17 * Tile.scale),
				GameButton.STD_HEIGHT + (2 * Tile.scale), 0xFF7F7F7F, "Menu", e);
	}

	Color thisColor = new Color(128, 128, 128, 220);

	public void renderMenu(Graphics g) {

		g.setColor(thisColor);
		g.fillRect(0, 0, Engine.WIDTH, Engine.HEIGHT);
		for (GameButton b : buttons) {

			b.update();
			;

		}

	}

	public void update() {

		if (!e.input.esc.isPressed()) {
			canExitMenu = true;
		}

		if (canExitMenu) {
			if (e.input.esc.isPressed()) {
				inMenu = false;
				canExitMenu = false;
			}

		}

		for (GameButton b : buttons)
			b.update();

		if (buttons[0].hasBeenClicked) {

			System.exit(0);

		}

		if (buttons[1].hasBeenClicked) {

			e.menu.startGame = false;
			inMenu = false;

		}

	}

}
