package kingery.ui.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.engine.InputHandler;
import kingery.game.islands.tiles.Tile;

public class GameButton implements GameComponent{

	private Rectangle buttonRect;

	public boolean hasBeenClicked = false;

	// private boolean canClick = true;
	public boolean attatchedToEntity = false;
	protected boolean onThis = false;
	private String string;
	public Color color;

	public int numTimesClicked;

	public int x, y, width, height;

	protected InputHandler input;


	public BufferedImage buttonImage;

	public static int STD_WIDTH = 32, STD_HEIGHT = 12;

	public GameButton(int x, int y, int width, int height, int color, String s) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = width;
		string = s;
		this.color = new Color(color);

		input = Engine.input;

		buttonRect = new Rectangle(x, y, width, height);

	}

	public GameButton(int x, int y, int width, int height, BufferedImage image) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		buttonImage = image;

		input = Engine.input;

		buttonRect = new Rectangle(x, y, width, height);

	}

	public GameButton(int x, int y, int color, String s) {

		this.x = x;
		this.y = y;
		this.width = STD_WIDTH;
		this.height = STD_HEIGHT;
		string = s;
		this.color = new Color(color);

		input = Engine.input;

		buttonRect = new Rectangle(x, y, width, height);

	}

	protected void onClick() {

	}

	public void update(Graphics g) {

		buttonRect.x = x * Tile.scale;
		buttonRect.y = y * Tile.scale;
		buttonRect.width = width * Tile.scale;
		buttonRect.height = height * Tile.scale;

		if (hasBeenClicked && !input.clicking()) {
			onClick();
			hasBeenClicked = false;
		}

		if (buttonRect.contains(input.MouseX, input.MouseY)) {

			onThis = true;

			if (input.clicking()) {
				hasBeenClicked = true;
			}

		} else {
			onThis = false;
		}

		render(Engine.g);

	}

	private void render(Graphics g) {

		g.setColor(color());
		g.fillRoundRect(x, y, width, height, 12, 12);
		g.setColor(Color.BLACK);
		
		drawCenteredString(g, string, buttonRect, new Font(Font.DIALOG, Font.BOLD, 5));
	}

	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		// Get the FontMetrics
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = this.x + (width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text
		int y = this.y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
		// Set the font
		g.setFont(font);
		// Draw the String
		g.drawString(text, x, y);
	}

	public Color color() {
		if (onThis)
			return color.brighter();
		return color;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
