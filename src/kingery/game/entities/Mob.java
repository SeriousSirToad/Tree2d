package kingery.game.entities;

import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.inventory.Inventory;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public abstract class Mob extends Entity {

	protected byte speed = 4;
	protected int xa, ya;

	int numsteps = 0;

	int collidedDir = 0;
	
	Inventory inventory;

	public Mob(int x, int y, String name, BufferedImage entityImage, Engine e, Island island) {
		super(x, y, name, true, entityImage, e, island);
		 inventory = new Inventory(e);
	}

	public void move() {
		moveY();
		moveX();
	}

	protected void moveX() {
		if (xa > 0) {// Moving right
			int tx = (int) (x + xa + collider.x + collider.width) / Tile.width;

			if (!collisionWithTile(tx, (int) (y + collider.y) / Tile.width)
					&& !collisionWithTile(tx, (int) (y + collider.y + collider.height) / Tile.width)) {
				x += xa;
			} else {
				x = tx * Tile.width - collider.x - collider.width - speed + 1;
			}
		} else if (xa < 0) {// Moving left
			int tx = (int) (x + xa + collider.x) / Tile.width;

			if (!collisionWithTile(tx, (int) (y + collider.y) / Tile.width)
					&& !collisionWithTile(tx, (int) (y + collider.y + collider.height) / Tile.width)) {
				x += xa;
			} else {
				x = tx * Tile.width + Tile.width - collider.x;
			}
		}
	}

	protected void moveY() {
		if (ya < 0) {// Up
			int ty = (int) (y + ya + collider.y) / Tile.width;

			if (!collisionWithTile((int) (x + collider.x) / Tile.width, ty)
					&& !collisionWithTile((int) (x + collider.x + collider.width) / Tile.width, ty)) {
				y += ya;
			} else {
				y = ty * Tile.width + Tile.width - collider.y;

			}
		} else if (ya > 0) {// Down
			int ty = (int) (y + ya + collider.y + collider.height) / Tile.width;

			if (!collisionWithTile((int) (x + collider.x) / Tile.width, ty)
					&& !collisionWithTile((int) (x + collider.x + collider.width) / Tile.width, ty)) {
				y += ya;
			} else {
				y = ty * Tile.width - collider.y - collider.height - 1;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {

		return island.getTile(x, y).isSolid();

	}

	protected boolean shouldExit(int x, int y) {
		if(island.getTile(x, y).getId() == 8)
			return true;
		else return false;
	}
	
	@Override
	public abstract void update();

}
