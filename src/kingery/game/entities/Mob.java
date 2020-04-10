package kingery.game.entities;

import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.inventory.Inventory;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public abstract class Mob extends Entity {

	protected byte speed = 1;
	protected int xa, ya;

	int numsteps = 0;

	int collidedDir = 0;

	public Inventory inventory;

	public Mob(int x, int y, String name, BufferedImage entityImage, Engine e, Island island) {
		super(x, y, name, true, entityImage, e, island);
		inventory = new Inventory(0, 0, 225, 225, e);
	}

	public void move() {
		if (!collisionWithTile(x, y)) {
			x += xa;
			y += ya;
		}
	}

	protected boolean collisionWithTile(int x, int y) {

		if (movingDir == 1)
			return island.getTile(x / Tile.width + 1, y / Tile.width + 1).isSolid();
		if (movingDir == 2)
			return island.getTile(x / Tile.width, y / Tile.width + 2).isSolid();
		if (movingDir == 3)
			return island.getTile((x - 1) / Tile.width, y / Tile.width + 1).isSolid();
		if (movingDir == 4)
			return island.getTile(x / Tile.width, ((y - 1) / Tile.width) + 1).isSolid();
		return false;

	}

	protected boolean shouldExit(int x, int y) {
		if (island.getTile(x, y).getId() == 8)
			return true;
		else
			return false;
	}

	@Override
	public abstract void update();

}
