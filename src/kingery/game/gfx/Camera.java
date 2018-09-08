package kingery.game.gfx;

import kingery.game.engine.Engine;
import kingery.game.entities.Entity;
import kingery.game.islands.tiles.Tile;

public class Camera {
	
	private static int x = 0;
	private static int y = 0;
	
	public static void centerOnEntity(Entity e) {
		x = e.x - (Engine.WIDTH / 2) * Engine.SCALE + e.width / 2;
		y = e.y - (Engine.HEIGHT / 2) * Engine.SCALE + e.height / 2;
	}
	
	public static int x() {
		return x;
	}
	
	public static int y() {
		return y;
	}
	
}
