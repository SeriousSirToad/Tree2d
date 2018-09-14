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
	static int w = (int) (Engine.WIDTH / 1.2), h = (int) (Engine.HEIGHT / 1.2);
	static int x = Engine.WIDTH / 2 - w / 2;
	static int y = Engine.HEIGHT / 2 - h / 2;
	public boolean active;
	private NPC npc;
	GameButton okay;

	static FontMetrics fm;

	static Color transluscentBackground = new Color(0xFF000000);

	public GameWindow(NPC attatchedNPC) {
		active = true;
		Engine.subwindows.add(this);
		this.npc = attatchedNPC;
		okay = new GameButton(x + w - (17 * Tile.scale), y + (GameButton.STD_HEIGHT), 0xFF00009F,
				"Okay", Menu.engine);
	}

	public void update(Graphics g) {
		if (npc.canShowBox) {
			active = true;
			showDialog(npc.name, npc.messages[npc.speechIndex], g);
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

		okay.update();

		if (okay.hasBeenClicked) {
			active = false;
			npc.canShowBox = false;
			npc.speechIndex++;
		}

	}

}
