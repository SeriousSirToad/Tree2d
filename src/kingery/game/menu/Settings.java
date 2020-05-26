package kingery.game.menu;

import kingery.ui.Organizer;
import kingery.ui.RenderOrder;
import kingery.ui.component.GameButton;
import kingery.ui.component.GameWindow;
import kingery.ui.component.Slider;

public class Settings {

	public static int frameCap = 60;
	public static int musicVolume = 0;
	public static int fxVolume = 0;

	public static GameButton[] buttons;
	public static GameWindow window;
	static Slider volumeSlider = new Slider(40, 52, 0);

	public static void init() {

		buttons = new GameButton[] {

				new GameButton(0, 0, 0xFF3F3F3F, "60") {
					public void onClick() {
						frameCap = 60;
					}
				},

				new GameButton(0, 0, 0xFF3F3F3F, "30") {
					public void onClick() {
						frameCap = 30;
					}
				},

				new GameButton(0, 0, 0xFF3F3F3F, "Infinite") {
					public void onClick() {
						frameCap = 0;
					}
				},

				new GameButton(0, 0, 0xFF009900, "Volume") {
					boolean sliderActive = false;

					public void onClick() {
						if (!sliderActive) {
							RenderOrder.add(volumeSlider);
							sliderActive = true;
						} else {
							RenderOrder.remove(volumeSlider);
							sliderActive = false;
						}
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

		window = new GameWindow() {
			public void close() {
				RenderOrder.remove(volumeSlider);
				InGameMenu.canExitMenu = false;
			}
		};
		window.buttons = buttons;

	}

}
