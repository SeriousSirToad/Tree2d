package kingery.ui.component;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import kingery.game.engine.Engine;
import kingery.game.engine.GameState;
import kingery.game.entities.npcs.NPC;

public class NPCWindow extends GameWindow {
	private NPC npc;
	GameButton okay;

	static FontMetrics fm;

	int w = (int) (GameState.camera.width / 1.2), h = (int) (GameState.camera.height / 1.2);
	int x = GameState.camera.width / 2 - w / 2;
	int y = GameState.camera.height / 2 - h / 2;

	public NPCWindow(NPC attatchedNPC) {
		Engine.subwindows.add(this);
		this.npc = attatchedNPC;
		buttons = new GameButton[1];
		buttons[0] = new GameButton(x + w - (GameButton.STD_WIDTH) - 1, y + h - (GameButton.STD_HEIGHT) - 1, 0xFF00009F,
				"Close");
	}

	public void update(Graphics2D g) {
		if (npc.canShowBox) {
			active = true;
			showDialog(npc.name, npc.messages[npc.speechIndex], g);
		}
	}

	public void showDialog(String title, String message, Graphics g) {
		g.setFont(font);
		fm = g.getFontMetrics();
		int sy = y + fm.getHeight() + 1;
		g.setColor(transluscentBackground);
		g.fillRect(x, y, w, h);
		g.setColor(Color.white);
		g.drawString(title, x + (1), y + fm.getHeight());
		g.drawLine(x, y + fm.getHeight() + (1), x + w, y + fm.getHeight() + (1));
		for (String line : message.split("\n"))
			g.drawString(line, x + (1), sy += fm.getHeight());

		for (GameButton b : buttons) {

			b.update(g);

		}
		if (buttons[0].hasBeenClicked) {
			active = false;
			npc.canShowBox = false;
			npc.speechIndex++;
		}

	}

	public void changeButtons(GameButton[] buttons) {

		this.buttons = buttons;

	}

}
