package kingery.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import kingery.game.engine.Engine;
import kingery.game.entities.npcs.NPC;
import kingery.game.islands.tiles.Tile;
import kingery.game.menu.Menu;

public class GameWindow {

	public GameButton[] buttons;
	Font font = new Font(Font.DIALOG, Font.BOLD, (int)(5.6 * Engine.SCALE));
	static int w = (int) (Engine.WIDTH), h = (int) (Engine.HEIGHT);
	static int x = Engine.WIDTH / 2 - w / 2;
	static int y = Engine.HEIGHT / 2 - h / 2;
	public boolean active;
	GameButton okay;
	String title = "", message = "";
	static FontMetrics fm;

	static Color transluscentBackground = new Color(0, 0, 0, 100);

	public GameWindow() {
		active = true;
		Engine.subwindows.add(this);
	}

	public void update(Graphics g) {

	}

	public void show() {
		Graphics g = Engine.g;
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		fm = g.getFontMetrics();
		int sx = x, sy = y + fm.getHeight() + (1 * Tile.scale);
		g.setColor(transluscentBackground);
		g.fillRect(x, y, w, h);
		g.setColor(Color.white);
		g.drawString(title, x + (1 * Tile.scale), y + fm.getHeight());
		g.drawLine(x, y + fm.getHeight() + (1 * Tile.scale), x + w, y + fm.getHeight() + (1 * Tile.scale));
		for (String line : message.split("\n"))
			g.drawString(line, x + (1 * Tile.scale), sy += fm.getHeight());

		for (GameButton b : buttons) {

			b.update(g);

		}

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

}
