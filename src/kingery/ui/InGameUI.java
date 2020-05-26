package kingery.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;

import javax.swing.text.html.parser.Entity;

import kingery.game.engine.GameState;
import kingery.game.menu.InGameMenu;
import kingery.ui.component.GameButton;
import kingery.ui.component.GameWindow;

public class InGameUI {

	static int moneyX = 173;
	static int moneyY = 3;
	static int moneyW = 80;
	static int moneyH = 20;

	static HashMap<Entity, Point> entities;
	static HashMap<GameWindow, Point> windows;

	static GameButton menubutton = new GameButton(219, 7, 0xFF420DAB, "Menu") {

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
