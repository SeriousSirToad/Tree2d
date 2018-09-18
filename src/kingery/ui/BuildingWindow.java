package kingery.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.entities.buildings.Building;
import kingery.game.islands.tiles.Tile;
import kingery.game.menu.Menu;

public class BuildingWindow extends GameWindow {

	GameButton[] buttons;
	static int w = (int) (Engine.WIDTH / 1.2), h = (int) (Engine.HEIGHT / 1.2);
	static int x = Engine.WIDTH / 2 - w / 2;
	static int y = Engine.HEIGHT / 2 - h / 2;
	public boolean active;
	static FontMetrics fm;
	BufferedImage backgroundImage;
	Building myBuilding;

	public BuildingWindow(String[] buttons, String path, Building b) {

		myBuilding = b;

		this.buttons = new GameButton[buttons.length + 1];

		for (int i = 0; i < buttons.length + 1; i++) {

			if (i == buttons.length) {
				this.buttons[i] = new GameButton(x + w - (17 * Tile.scale), y
						+ h - (GameButton.STD_HEIGHT) - (1 * Tile.scale),
						0xFF00009F, "Close", Menu.engine);
			} else {
				this.buttons[i] = new GameButton(x + w - (17 * Tile.scale), y
						+ GameButton.STD_HEIGHT + (GameButton.STD_HEIGHT * i)
						+ (i * Tile.scale), 0xFF009F00, buttons[i], Menu.engine);
			}

		}
	}

	public void update(Graphics g) {
		if (myBuilding.inside) {
			System.out.println("pippa what");
			showDialog(myBuilding.bldg_name, myBuilding.bldg_desc, Engine.g);
		}
	}

	public void showDialog(String title, String message, Graphics g) {
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		fm = g.getFontMetrics();
		int sx = x, sy = y + fm.getHeight() + (1 * Tile.scale);
		g.setColor(transluscentBackground);
		g.fillRect(x, y, w, h);
		g.setColor(Color.white);
		g.drawString(title, x + (1 * Tile.scale), y + fm.getHeight());
		g.drawLine(x, y + fm.getHeight() + (1 * Tile.scale), x + w,
				y + fm.getHeight() + (1 * Tile.scale));
		for (String line : message.split("\n"))
			g.drawString(line, x + (1 * Tile.scale), sy += fm.getHeight());

		for (GameButton b : buttons) {

			b.update();

		}

		if (buttons[buttons.length - 1].hasBeenClicked) {
			active = false;
			myBuilding.inside = false;
		}

	}

}
