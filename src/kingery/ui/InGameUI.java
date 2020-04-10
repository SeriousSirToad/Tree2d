package kingery.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;

import javax.swing.text.html.parser.Entity;

import kingery.game.engine.Engine;
import kingery.game.engine.GameState;
import kingery.game.islands.tiles.Tile;
import kingery.game.menu.InGameMenu;

public class InGameUI {

	static int moneyX = 138;
	static int moneyY = 2;
	static int moneyW = 116;
	static int moneyH = 16;
	
	static HashMap<Entity, Point> entities;
	static HashMap<GameWindow, Point> windows;
	
	static GameButton menubutton = new GameButton(moneyX + moneyW - (GameButton.STD_WIDTH) - (1), moneyY, 0xFF420DAB,
			"Menu") {

		public void onClick() {
			InGameMenu.inMenu = true;
		}

	};

	public static void render(Graphics g) {
		String playerMoneyAmnt = "$" + GameState.p.money;
		g.setColor(Color.WHITE);
		g.fillRect(moneyX - 1, moneyY - 1, moneyW + 2, moneyH + 2);
		g.setColor(Color.black);
		g.fillRect(moneyX, moneyY, moneyW, moneyH);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 8));
		g.setColor(Color.green);
		g.drawString(playerMoneyAmnt, moneyX + (moneyW / 6) - (g.getFontMetrics().stringWidth(playerMoneyAmnt) / 2),
				moneyY + (moneyH / 2) + (g.getFontMetrics().getHeight() / 4));
		menubutton.update(g);
	}

}
