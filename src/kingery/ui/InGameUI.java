package kingery.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import kingery.game.engine.Engine;
import kingery.game.engine.EntityHandler;
import kingery.game.islands.tiles.Tile;
import kingery.game.menu.InGameMenu;

public class InGameUI {

	static int moneyX = (int) (138 * Engine.SCALE);
	static int moneyY = (int) (2 * Engine.SCALE);
	static int moneyW = (int) (116 * Engine.SCALE);
	static int moneyH = (int) (16 * Engine.SCALE);
	static GameButton menubutton = new GameButton(moneyX + moneyW - (GameButton.STD_WIDTH) - (1 * Tile.scale), moneyY, 0xFF420DAB, "Menu") {

		public void onClick() {
			Engine.inMenu.inMenu = true;
		}

	};

	public static void render(Graphics g) {
		String playerMoneyAmnt = "$" + EntityHandler.p.money;
		g.setColor(Color.WHITE);
		g.fillRect(moneyX - (int) (1 * Engine.SCALE), moneyY - (int) (1 * Engine.SCALE),
				moneyW + (int) (2 * Engine.SCALE), moneyH + (int) (2 * Engine.SCALE));
		g.setColor(Color.black);
		g.fillRect(moneyX, moneyY, moneyW, moneyH);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, (int) (8 * Engine.SCALE)));
		g.setColor(Color.green);
		g.drawString(playerMoneyAmnt, moneyX + (moneyW / 6) - (g.getFontMetrics().stringWidth(playerMoneyAmnt) / 2),
				moneyY + (moneyH / 2) + (g.getFontMetrics().getHeight() / 4));
		InGameMenu inMenu = Engine.inMenu;
		menubutton.update(g);
	}

}
