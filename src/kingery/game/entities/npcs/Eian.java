package kingery.game.entities.npcs;

import kingery.game.engine.Engine;
import kingery.game.gfx.Assets;
import kingery.game.islands.Island;
import kingery.ui.GameButton;
import kingery.ui.Organizer;

public class Eian extends NPC {

	static String[] eianMessages = { "Beans sir.. Spare bears sir?..", "I need beans sir..beans..I desire beans..." };

	public Eian(Engine e) {
		super(40, 22, eianMessages, "Eian", Assets.getLumberjack(0), e, Island.Utopia);

		GameButton[] buttons = new GameButton[] {

				new GameButton(0, 0, 0xFF339933, "Gimme beans B")

		};

		Organizer.organizeRight(buttons, dialogWindow);

		dialogWindow.changeButtons(buttons);
	}

}
