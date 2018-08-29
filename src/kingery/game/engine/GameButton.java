package kingery.game.engine;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import kingery.game.gfx.Camera;

public class GameButton {

	private Rectangle buttonRect;

	public boolean hasBeenClicked = false;

	private boolean canClick = true;
	public boolean attatchedToEntity = false;

	public int numTimesClicked;

	public int x, y, width, height;

	private InputHandler input;

	private Engine e;

	public BufferedImage buttonImage;

	public GameButton(int x, int y, int width, int height, Engine e) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.e = e;

		input = e.input;

		buttonRect = new Rectangle(x, y, width, height);

		e.buttons.add(this);

	}

	public GameButton(int x, int y, int width, int height, String buttonPath, Engine e) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.e = e;

		try {
			buttonImage = ImageIO.read(new File("res/Images/Buttons/" + buttonPath + ".png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		input = e.input;

		buttonRect = new Rectangle(x, y, width, height);

		e.buttons.add(this);

	}

	@SuppressWarnings("deprecation")
	public void update() {

		for (GameButton b : e.buttons) {
			if (b != this) {
				if (b.buttonRect.contains(input.MouseX, input.MouseY)) {
					return;
				}
			}
		}
		if (buttonRect.contains(input.MouseX, input.MouseY)) {

			e.frame.setCursor(Cursor.HAND_CURSOR);

			if (input.clicking()) {
				hasBeenClicked = true;
				canClick = false;
				e.frame.setCursor(Cursor.DEFAULT_CURSOR);

			}

		} else {
			e.frame.setCursor(Cursor.DEFAULT_CURSOR);
		}
	}

}
