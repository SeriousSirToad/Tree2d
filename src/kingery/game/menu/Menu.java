package kingery.game.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import kingery.game.engine.Engine;
import kingery.game.engine.GameState;
import kingery.ui.component.GameButton;

public class Menu {

	protected BufferedImage menuImage;

	public GameButton start;
	public GameButton exit;

	public boolean startGame = false;


	public Menu(Engine engine) {

		try {
			menuImage = ImageIO.read(new File("res/Images/titlescreen.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		start = new GameButton((GameState.camera.width / 2) - GameButton.STD_WIDTH / 2,
				(GameState.camera.height / 2) - GameButton.STD_HEIGHT * 2, 0xFF009F00, "Start") {

			public void onClick() {

				startGame = true;

			}

		};
		exit = new GameButton((GameState.camera.width / 2) - GameButton.STD_WIDTH / 2,
				(GameState.camera.height / 2) - GameButton.STD_HEIGHT / 2, 0xFF9F0000, "Yeet") {

			public void onClick() {
				System.exit(0);
			}

		};

	}

	public void update() {
		// exit.update();

		if (Engine.p.input.space.isPressed()) {
			startGame = true;
		}

	}

	public void renderTitle(Graphics g) {

		g.setColor(new Color(0xFF7F7F7F));
		g.fillRect(0, 0, Engine.WIDTH, Engine.HEIGHT);
		start.update(g);
		exit.update(g);

	}

	public boolean canStartGame() {

		return startGame;

	}

}
