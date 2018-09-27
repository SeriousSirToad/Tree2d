package kingery.game.gfx.lighting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;

public class BigLight{

	public BufferedImage image;
	public Color colour = new Color(0, 0, 0, 0);
	public static Color early = new Color(0, 0, 0, 200);
	public static Color afternoon = new Color(0, 0, 0, 0);
	public static Color morning = new Color(0, 0, 0, 154);
	public static Color evening = new Color(0, 0, 0, 127);
	
	public void render(Graphics g2) {
		g2.setColor(colour);
		g2.fillRect(0, 0, Engine.WIDTH, Engine.HEIGHT);
	}

}
