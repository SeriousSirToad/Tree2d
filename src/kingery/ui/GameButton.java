package kingery.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.engine.InputHandler;

public class GameButton {

	private Rectangle buttonRect;

	public boolean hasBeenClicked = false;

	private boolean canClick = true;
	public boolean attatchedToEntity = false;
	protected boolean onThis = false;
	private String string;
	public Color color;

	public int numTimesClicked;

	public int x, y, width, height;

	protected InputHandler input;

	private Engine e;

	public BufferedImage buttonImage;

	public static int STD_WIDTH = 16 * (int) (Engine.SCALE * 2),
			STD_HEIGHT = 8 * (int) (Engine.SCALE * 2);

	public GameButton(int x, int y, int width, int height, int color, Engine e) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.e = e;

		this.color = new Color(color);

		input = e.input;

		buttonRect = new Rectangle(x, y, width, height);

		e.buttons.add(this);

	}

	public GameButton(int x, int y, int width, int height, BufferedImage image,
			Engine e) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.e = e;

		buttonImage = image;

		input = e.input;

		buttonRect = new Rectangle(x, y, width, height);

		e.buttons.add(this);

	}

	public GameButton(int x, int y, int color, String s, Engine e) {

		this.x = x;
		this.y = y;
		this.width = STD_WIDTH;
		this.height = STD_HEIGHT;
		this.e = e;
		string = s;
		this.color = new Color(color);

		input = e.input;

		buttonRect = new Rectangle(x, y, width, height);

		e.buttons.add(this);

	}

	protected void updates() {

	}
	
	public void update(Graphics g) {
		
		buttonRect.x = x;
		buttonRect.y = y;
		
		if (hasBeenClicked)
			hasBeenClicked = false;

		if (buttonRect.contains(input.MouseX, input.MouseY)) {

			onThis = true;

			if (input.clicking()) {
				hasBeenClicked = true;
				canClick = false;
			}

		} else {
			onThis = false;
		}

		updates();
		
		render(Engine.g);
		
	}

	private void render(Graphics g) {

		g.setColor(color());
		g.fillRoundRect(x, y, width, height, 16, 16);
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		g.drawString(string,
				x + width / 2 - g.getFontMetrics().stringWidth(string) / 2, y
						+ (height / 2) + (g.getFontMetrics().getHeight() / 4));

	}

	public Color color() {
		if (onThis)
			return color.brighter();
		return color;
	}

}
