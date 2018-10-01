package kingery.game.menu;

import java.awt.Color;
import java.awt.Graphics;

import kingery.game.engine.Engine;
import kingery.game.islands.tiles.Tile;
import kingery.ui.GameButton;

public class InGameMenu {

	public boolean inMenu = false;
	public Engine e;
	GameButton[] buttons = new GameButton[3];
	public boolean canExitMenu = false;
	public boolean inSettings = false;

	public InGameMenu(Engine engine) {
		e = engine;
		buttons[0] = new GameButton(0, 0, 0xFF9F0000, "Exit");
		buttons[1] = new GameButton(0, 0, 0xFF7F7F7F, "Main Menu");
		buttons[2] = new GameButton(0, 0, 0xFF3F3F3F, "Settings") {
			public void onClick() {

			}
		};

		for (int i = 0; i < buttons.length; i++) {
			Settings.organize(buttons[i]);
		}

	}

	Color thisColor = new Color(128, 128, 128, 220);

	public void renderMenu(Graphics g) {

		g.setColor(thisColor);
		g.fillRect(0, 0, Engine.WIDTH, Engine.HEIGHT);
		for (GameButton b : buttons) {

			b.update(g);

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

		if (buttons[0].hasBeenClicked) {

			System.exit(0);

		}

		if (buttons[1].hasBeenClicked) {

			e.menu.startGame = false;
			inMenu = false;
			canExitMenu = false;

		}

	}

}
