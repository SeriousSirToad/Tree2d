package kingery.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import kingery.game.engine.Engine;
import kingery.game.islands.tiles.Tile;

public class GameWindow {

	public GameButton[] buttons;
	Font font = new Font(Font.DIALOG, Font.BOLD, 5);
	public int w = (int) (Engine.WIDTH), h = (int) (Engine.HEIGHT);
	public int x = Engine.WIDTH / 2 - w / 2;
	public int y = Engine.HEIGHT / 2 - h / 2;
	public boolean active;
	GameButton okay;
	String title = "", message = "";
	static FontMetrics fm;

	static Color transluscentBackground = new Color(0, 0, 0, 100);

	public GameWindow() {
		Engine.subwindows.add(this);
	}

	public void update(Graphics2D g) {
		if (active) {
			show(g);
		}
	}

	public void show(Graphics2D g) {

		g.setColor(transluscentBackground);
		g.fillRect(x, y, w, h);
		for (GameButton b : buttons) {
			b.update(g);
		}

	}

	public void showText() {
		Graphics g = Engine.g;
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		fm = g.getFontMetrics();
		int sy = y + fm.getHeight() + (1 * Tile.scale);
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
