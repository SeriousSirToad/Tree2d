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

public class BuildingWindow {

	GameButton[] buttons;
	static int w = (int) (Engine.WIDTH / 1.2), h = (int) (Engine.HEIGHT / 1.2);
	static int x = Engine.WIDTH / 2 - w / 2;
	static int y = Engine.HEIGHT / 2 - h / 2;
	public boolean active;
	static FontMetrics fm;
	BufferedImage backgroundImage;
	Building myBuilding;

	static Color transluscentBackground = new Color(0xFF000000);

	public BuildingWindow(String[] buttons, String path, Building b) {
		
		myBuilding = b;
		
		this.buttons = new GameButton[buttons.length];
		for (int i = 0; i < buttons.length; i++) {

			this.buttons[i] = new GameButton(x + w - (17 * Tile.scale), y
					+ (GameButton.STD_HEIGHT * i) - (i * Tile.scale), 0xFF, buttons[i],
					Menu.engine);

		}
	}

	public void update(Graphics g) {
		if(myBuilding.inside){
			showDialog(myBuilding.bldg_name, myBuilding.bldg_desc, Engine.g);
		}
	}

	public void showDialog(String title, String message, Graphics g) {
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		fm = g.getFontMetrics();
		int sx = x, sy = y + fm.getHeight();
		g.setColor(transluscentBackground);
		g.fillRect(x, y, w, h);
		g.setColor(Color.white);
		g.drawString(title, x, y + fm.getHeight());
		g.drawLine(x, y + fm.getHeight() + 3, x + w, y + fm.getHeight() + 3);
		for (String line : message.split("\n"))
			g.drawString(line, x, sy += fm.getHeight());

		for (GameButton b : buttons) {

			b.update();

		}

		if (buttons[0].hasBeenClicked) {
			active = false;
			myBuilding.inside = false;
		}

	}

}
