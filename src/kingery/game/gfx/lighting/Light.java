package kingery.game.gfx.lighting;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import kingery.game.engine.GameState;
import kingery.game.islands.tiles.Tile;

public class Light {

	public float x, y;
	public int radius;
	public Point2D center = new Point2D.Float();
	public Color lightColor;
	public Color[] colours;

	public Light(float x, float y, int radius, Color colour) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		lightColor = colour;
		System.out.println(lightColor.getAlpha());
		colours = new Color[] { lightColor, Color.black };
	}

	public void update() {
		center = new Point2D.Float((x * Tile.width) + Tile.width / 2 - GameState.camera.x(),
				(y * Tile.width) + Tile.width / 2 - GameState.camera.y());
	}

	public void render(Graphics2D g2) {
		float[] dist = { 0.0f, 1.0f };
		RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colours);
		g2.setPaint(p);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .95f));
	}

}
