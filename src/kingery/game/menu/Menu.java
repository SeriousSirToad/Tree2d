package kingery.game.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import kingery.game.engine.Engine;
import kingery.game.engine.GameButton;

public class Menu {

	protected BufferedImage menuImage;

	public GameButton start;
	public GameButton exit;

	private boolean startGame = false;

	protected Engine engine;

	int renderX;
	int renderY;

	public Menu(Engine engine) {

		this.engine = engine;

		try {
			menuImage = ImageIO.read(new File("res/Images/titlescreen.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		renderX = (Engine.HEIGHT);
		renderY = (Engine.WIDTH);

		int bbbbb = (menuImage.getWidth());
		int bbbb = (menuImage.getHeight());

		start = new GameButton(297 + Engine.WIDTH / 2 - menuImage.getWidth() / 2,
				381 + Engine.HEIGHT / 2 - menuImage.getHeight() / 2, 52, 24, engine);
		exit = new GameButton(300 + renderX / 2 - menuImage.getWidth() / 2,
				444 + renderY / 2 - menuImage.getHeight() / 2, 48, 26, engine);

	}

	public void update() {

		start.update();
		exit.update();

		if (start.hasBeenClicked) {

			startGame = true;

		}

		if (exit.hasBeenClicked) {

			System.exit(0);

		}

	}

	public void renderTitle(Graphics g) {

		g.drawImage(menuImage, renderY / 2 - menuImage.getWidth() / 2, renderX / 2 - menuImage.getHeight() / 2, null);

	}

	public boolean canStartGame() {

		return startGame;

	}

}
