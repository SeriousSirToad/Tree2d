package kingery.game.menu;

import java.awt.Color;
import java.awt.Graphics;

import kingery.game.engine.Engine;
import kingery.game.engine.GameButton;

public class InGameMenu {

	public boolean inMenu = false;
	public Engine e;
	GameButton exit;
	public boolean canExitMenu = false;

	public InGameMenu(Engine engine) {
		e = engine;
		exit = new GameButton(e.WIDTH / 2 - 16, e.HEIGHT / 2 - 32, 64, 32, "exitButton", e);
	}

	Color thisColor = new Color(128, 128, 128, 220);

	public void renderMenu(Graphics g) {

		g.setColor(thisColor);
		g.fillRect(0, 0, e.WIDTH, e.HEIGHT);
		g.drawImage(exit.buttonImage, exit.x, exit.y, null);

	}

	public void update() {

		if (!e.input.esc.isPressed()) {
			canExitMenu = true;
		}

		if (canExitMenu) {
			if (e.input.esc.isPressed()) {
				inMenu = false;
				canExitMenu = false;
			} else {
				exit.update();
			}

		}

		if (exit.hasBeenClicked) {

			System.exit(0);

		}

	}

}
