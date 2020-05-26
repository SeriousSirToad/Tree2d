package kingery.game.gfx;

import java.awt.Rectangle;

import kingery.game.engine.Engine;
import kingery.game.entities.Entity;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class Camera {
	public int x, y;
	private Entity entity;
	private Island level;

	public int width = Engine.WIDTH;
	public int height = Engine.HEIGHT;

	public Camera(int x, int y, Entity attatchedEntity) {

		this.x = 0;
		this.y = 0;
		this.entity = attatchedEntity;
		this.level = entity.getLevel();

	}

	public Camera(int x, int y, Island level) {
		this.x = 0;
		this.y = 0;
		this.level = level;
	}

	public void tick() {
		if (entity != null) {
			centerOnEntity(entity);
		}

		if (level.scaledWidth() <= Engine.width()) {
			this.x = ((level.scaledWidth() / 2) - Engine.width() / 2) / Tile.scale;
		} else {
			if (x < 0) {
				x = 0;
			}
			if (x + Engine.WIDTH > level.width * Tile.width) {
				x = level.width * Tile.width - Engine.WIDTH;
			}
		}

		if (level.scaledHeight() <= Engine.height()) {
			this.y = ((level.scaledHeight() / 2) - Engine.height() / 2) / Tile.scale;
		} else {
			if (y < 0) {
				y = 0;
			}
			if (y + Engine.height() / Tile.scale > level.height * Tile.width) {
				y = level.height * Tile.width - Engine.height() / Tile.scale;
			}
		}
	}

	public void centerOnEntity(Entity e) {
		if (level != entity.getLevel()) {
			level = entity.getLevel();
		}
		x = e.x - (width / 2) + e.width / 2;
		y = e.y - (height / 2) + e.height / 2;
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
	}

	public boolean contains(Entity e) {
		if (e.x < x + width && e.x + e.width > x) {
			if (e.y < y + height && e.y + e.height > y) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(Rectangle e) {
		if (e.x < x + width && e.x + e.width > x) {
			if (e.y < y + height && e.y + e.height > y) {
				return true;
			}
		}
		return false;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

}
