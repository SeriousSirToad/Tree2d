package kingery.game.entities.npcs;

import kingery.game.gfx.Assets;
import kingery.game.islands.Island;
import kingery.ui.Organizer;
import kingery.ui.component.GameButton;

public class Eian extends NPC {

	static String[] eianMessages = { "Beans sir.. Spare bears sir?..", "I need beans sir..beans..I desire beans..." };

	public Eian(int x, int y, Island island) {
		super(x, y, eianMessages, "Eian", Assets.getLumberjack(0), island);

		GameButton[] buttons = new GameButton[] {

				new GameButton(0, 0, 0xFF339933, "Gimme beans B")

		};

		Organizer.organizeRight(buttons, dialogWindow);

		dialogWindow.changeButtons(buttons);
	}

}
