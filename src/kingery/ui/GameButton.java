package kingery.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.engine.InputHandler;
import kingery.game.islands.tiles.Tile;
import kingery.game.menu.Menu;

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

	private Engine e = Menu.engine;

	public BufferedImage buttonImage;

	public static int STD_WIDTH = 32 * (int) (Engine.SCALE * 2), STD_HEIGHT = 8 * (int) (Engine.SCALE * 2);

	public GameButton(int x, int y, int width, int height, int color, String s) {

		this.x = x;
		this.y = y;
		this.width = Engine.scale(width);
		this.height = Engine.scale(height);
		string = s;
		this.color = new Color(color);

		input = e.input;

		buttonRect = new Rectangle(x, y, width, height);

	}

	public GameButton(int x, int y, int width, int height, BufferedImage image) {

		this.x = x;
		this.y = y;
		this.width = Engine.scale(width);
		this.height = Engine.scale(height);

		buttonImage = image;

		input = e.input;

		buttonRect = new Rectangle(x, y, width, height);

	}

	public GameButton(int x, int y, int color, String s) {

		this.x = x;
		this.y = y;
		this.width = STD_WIDTH;
		this.height = STD_HEIGHT;
		string = s;
		this.color = new Color(color);

		input = e.input;

		buttonRect = new Rectangle(x, y, width, height);

	}

	protected void onClick() {

	}

	public void update(Graphics g) {

		if (Engine.g.getFontMetrics().stringWidth(string) > width) {
			this.width += (width % Engine.g.getFontMetrics().stringWidth(string));
			this.height = STD_HEIGHT;
			x -= width % Engine.g.getFontMetrics().stringWidth(string);
		}
		buttonRect.x = x;
		buttonRect.y = y;
		buttonRect.width = width;
		buttonRect.height = height;

		if (hasBeenClicked && !input.clicking()) {
			onClick();
			hasBeenClicked = false;
		}

		if (buttonRect.contains(input.MouseX, input.MouseY)) {

			onThis = true;

			if (input.clicking()) {
				hasBeenClicked = true;
				canClick = false;
			}

		} else {
			onThis = false;
		}

		render(Engine.g);

	}

	private void render(Graphics g) {

		g.setColor(color());
		g.fillRoundRect(x, y, width, height, (int) (12.8 * Engine.SCALE), (int) (12.8 * Engine.SCALE));
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, (int) (5.6 * Engine.SCALE)));
		g.drawString(string, x + width / 2 - g.getFontMetrics().stringWidth(string) / 2,
				y + (height / 2) + (g.getFontMetrics().getHeight() / 4));

	}

	public Color color() {
		if (onThis)
			return color.brighter();
		return color;
	}

}
