package kingery.game.menu;

import kingery.ui.GameButton;
import kingery.ui.GameWindow;
import kingery.ui.Organizer;

public class Settings {

	public static int frameCap = 60;
	public static int musicVolume = 0;
	public static int fxVolume = 0;

	public static GameButton[] buttons;
	public static GameWindow window;

	public static void init() {

		buttons = new GameButton[] {

				new GameButton(0, 0, 0xFF3F3F3F, "60") {
					public void onClick() {
						frameCap = 60;
					}
				},

				new GameButton(0, 0, 0xFF3F3F3F, "Infinite") {
					public void onClick() {
						frameCap = 0;
					}
				},

				new GameButton(0, 0, 0xFF7F0000, "Close") {
					public void onClick() {
						window.active = false;
					}
				}

		};

		for (int i = 0; i < buttons.length; i++) {
			Organizer.organizeLeft(buttons);
		}

		window = new GameWindow();
		window.buttons = buttons;

	}

	
}
