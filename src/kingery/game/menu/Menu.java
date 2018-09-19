package kingery.game.menu;

import java.awt.Color;
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

		start = new GameButton((int) (Engine.WIDTH * Engine.SCALE / 2)
				- GameButton.STD_WIDTH / 2,
				(int) (Engine.HEIGHT * Engine.SCALE / 2)
						- GameButton.STD_HEIGHT * 2, 0xFF009F00, "Start", engine) {
			
			public void update() {
				
				
				
			}
			
		};
		exit = new GameButton((int) (Engine.WIDTH * Engine.SCALE / 2)
				- GameButton.STD_WIDTH / 2,
				(int) (Engine.HEIGHT * Engine.SCALE / 2)
						- GameButton.STD_HEIGHT / 2, 0xFF9F0000, "Yeet", engine);

	}

	public void update() {
		// exit.update();

		if (start.hasBeenClicked || Engine.p.input.space.isPressed()) {

			startGame = true;

		}

		if (exit.hasBeenClicked) {

			System.exit(0);

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
