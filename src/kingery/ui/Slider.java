package kingery.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.islands.tiles.Tile;

public class Slider implements GameComponent {

	Rectangle slideBar = new Rectangle(2, 4);
	Rectangle container = new Rectangle(64, 32);

	private int maxValue, minValue = 0;
	private int currentValue;
	private int realValue;
	private boolean changing = false;
	
	private BufferedImage sliderImage;

	public Slider(int x, int y, int realValue) {
		this.realValue = realValue;
		container = new Rectangle(x, y, 64, 32);
		slideBar.x = currentValue + container.x;
		slideBar.y = container.y + container.height / 2;
		this.maxValue = container.width - slideBar.width;
	}

	public void render() {

		int mouseX = Engine.input.MouseX / Tile.scale;
		int mouseY = Engine.input.MouseY / Tile.scale;

		Graphics2D g = Engine.g;
		g.setColor(Color.black);
		if(sliderImage != null)
			g.drawImage(sliderImage, container.x, container.y, null);
		g.draw(container);
		g.draw(slideBar);

		if (slideBar.contains(mouseX, mouseY)) {
			System.out.println("cringe");
			if (Engine.input.clicking()) {
				changing = true;
			}
		}
		if (!Engine.input.clicking()) {
			changing = false;
		}

		if (changing) {
			System.out.println("overload " + (mouseX - container.x > maxValue));
			if (mouseX - container.x > maxValue) {
				currentValue = maxValue;
			} else if (mouseX - container.x < minValue) {
				currentValue = minValue;
			} else {
				currentValue = mouseX - container.x;
			}

			slideBar.x = container.x + currentValue;

		}

		realValue = (int) ((double) currentValue / maxValue * 100);

	}
	
	public void setImage(BufferedImage image) {
		this.sliderImage = image;
	}
	
	public int getValue() {
		return realValue;
	}

	@Override
	public int getX() {
		return container.x;
	}

	@Override
	public int getY() {
		return container.y;
	}

	@Override
	public int getWidth() {
		return container.width;
	}

	@Override
	public int getHeight() {
		return container.height;
	}

}
