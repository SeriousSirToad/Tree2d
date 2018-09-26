package kingery.ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import kingery.game.engine.Engine;
import kingery.game.entities.buildings.Building;
import kingery.game.islands.tiles.Tile;

public class BuildingWindow extends GameWindow {

	GameButton[] buttons;
	static int w = (int) (Engine.WIDTH / 1.2), h = (int) (Engine.HEIGHT / 1.2);
	static int x = Engine.WIDTH / 2 - w / 2;
	static int y = Engine.HEIGHT / 2 - h / 2;
	public boolean active;
	static FontMetrics fm;
	BufferedImage backgroundImage;
	Building myBuilding;
	public String actionText = "";
	public static boolean isOpen;

	public BuildingWindow(GameButton[] buttons, String path, Building b) {

		myBuilding = b;

		title = b.bldg_name;
		message = b.bldg_desc;

		try {
			backgroundImage = ImageIO.read(new File("res/Images/interiors/" + path + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.buttons = new GameButton[buttons.length + 1];

		for (int i = 0; i < buttons.length + 1; i++) {

			if (i == buttons.length) {
				this.buttons[i] = new GameButton(x + w - (17 * Tile.scale),
						y + h - (GameButton.STD_HEIGHT) - (1 * Tile.scale), 0xFF00009F, "Close") {

					public void onClick() {
						active = false;
						myBuilding.inside = false;
						actionText = "";
					}

				};
			} else {
				this.buttons[i] = buttons[i];
				this.buttons[i].x = x + w - (buttons[i].width) - 1 * Tile.scale;
				this.buttons[i].y = y + GameButton.STD_HEIGHT + (GameButton.STD_HEIGHT * i) + (i * Tile.scale);
			}

		}
	}

	public void update(Graphics2D g) {

		if (myBuilding.inside) {
			show(g);
			active = true;
			isOpen = true;
		} else {
			isOpen = false;
		}

	}

	public void show(Graphics2D g) {
		g.drawImage(backgroundImage, 0, 0, Engine.WIDTH, Engine.HEIGHT, null);
		g.setFont(font);
		fm = g.getFontMetrics();
		int sx = x + 1 * Tile.scale, sy = y + fm.getHeight() + (1 * Tile.scale);
		g.setColor(transluscentBackground);
		g.fillRect(x, y, w, h);
		g.setColor(Color.white);
		g.drawString(title, sx, y + fm.getHeight());
		g.drawLine(x, y + fm.getHeight() + (1 * Tile.scale), x + w, y + fm.getHeight() + (1 * Tile.scale));
		for (String line : message.split("\n"))
			g.drawString(line, x + (1 * Tile.scale), sy += fm.getHeight());

		g.drawString(actionText, sx, y + h - 1 * Tile.scale);

		for (GameButton b : buttons) {

			b.update(g);

		}

	}

}
