package kingery.game.gfx;

import java.awt.Rectangle;

import kingery.game.engine.Engine;
import kingery.game.entities.Entity;
import kingery.game.islands.tiles.Tile;

public class Camera {
	
	private static int x = 0;
	private static int y = 0;
	
	public static int width = Engine.WIDTH / Tile.scale;
	public static int height = Engine.HEIGHT / Tile.scale;
	
	public static void centerOnEntity(Entity e) {
		x = e.x - (width / 2) + e.width / 2;
		y = e.y - (height / 2) + e.height / 2;
	}
	
	public static boolean contains(Entity e) {
		if(e.x < x + width && e.x + e.width > x) {
			if(e.y < y + height && e.y + e.height > y) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean contains(Rectangle e) {
		if(e.x < x + width && e.x + e.width > x) {
			if(e.y < y + height && e.y + e.height > y) {
				return true;
			}
		}
		return false;
	}
	
	public static int x() {
		return x;
	}
	
	public static int y() {
		return y;
	}
	
}
