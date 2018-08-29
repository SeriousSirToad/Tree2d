package kingery.game.buildings;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Building {

	public Rectangle doorRect;
	public Rectangle collider;
	public Rectangle behindRect;
	public Interior interior;

	public int gx, gy, dx, dy, dw, dh;

	public Building(int gx, int gy, int gw, int gh, int dx, int dy, int dd) {

		doorRect = new Rectangle(dx, dy, 88, 20);
		collider = new Rectangle(gx, gy, gw, gh);

	}

}
