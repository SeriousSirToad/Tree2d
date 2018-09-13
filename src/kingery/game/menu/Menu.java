package kingery.game.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import kingery.game.engine.Engine;
import kingery.ui.GameButton;

public class Menu {

	protected BufferedImage menuImage;

	public GameButton start;
	public GameButton exit;

	public boolean startGame = false;

	public static Engine engine;

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

		start = new GameButton((int)(Engine.WIDTH * Engine.SCALE / 2), (int)(Engine.HEIGHT * Engine.SCALE / 2), 0xFF009F00, "Start", engine);
		//exit = new GameButton(300 + Engine.WIDTH / 2 - Engine.HEIGHT / 2,
				//444 + Engine.HEIGHT / 2 - menuImage.getHeight() / 2, 48, 26, engine);

	}

	public void update() {

		start.update();
		//exit.update();

		if (start.hasBeenClicked || Engine.p.input.space.isPressed()) {

			startGame = true;

		}

		//if (exit.hasBeenClicked) {

			//System.exit(0);

		//}

	}

	public void renderTitle(Graphics g) {

		g.drawImage(menuImage, 0, 0, Engine.WIDTH, Engine.HEIGHT, null);

	}

	public boolean canStartGame() {

		return startGame;

	}

}
