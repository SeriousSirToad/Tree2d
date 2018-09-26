package kingery.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import kingery.game.engine.Engine;
import kingery.game.entities.npcs.NPC;
import kingery.game.islands.tiles.Tile;
import kingery.game.menu.Menu;

public class NPCWindow extends GameWindow {
	private NPC npc;
	GameButton okay;

	static FontMetrics fm;

	static int w = (int) (Engine.WIDTH / 1.2), h = (int) (Engine.HEIGHT / 1.2);
	static int x = Engine.WIDTH / 2 - w / 2;
	static int y = Engine.HEIGHT / 2 - h / 2;

	public NPCWindow(NPC attatchedNPC) {
		active = true;
		Engine.subwindows.add(this);
		this.npc = attatchedNPC;
		buttons = new GameButton[1];
		buttons[0] = new GameButton(x + w - (17 * Tile.scale), y + h - (GameButton.STD_HEIGHT) - (1 * Tile.scale),
				0xFF00009F, "Close");
	}

	public void update(Graphics g) {
		if (npc.canShowBox) {
			active = true;
			showDialog(npc.name, npc.messages[npc.speechIndex], g);
		}
	}

	public void showDialog(String title, String message, Graphics g) {
		g.setFont(font);
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
		if (buttons[0].hasBeenClicked) {
			active = false;
			npc.canShowBox = false;
			npc.speechIndex++;
		}

	}
}
