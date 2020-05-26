package kingery.game.entities;

import java.awt.image.BufferedImage;

import kingery.game.inventory.Inventory;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public abstract class Mob extends Entity {

	protected byte speed = 1;
	protected int xa, ya;

	int numsteps = 0;

	int collidedDir = 0;

	public Inventory inventory;

	public Mob(int x, int y, String name, BufferedImage entityImage, Island island) {
		super(x, y, name, true, entityImage, island);
		inventory = new Inventory(0, 0, 225, 225);
	}

	public void move() {
		if (!collisionWithTile(x, y)) {
			x += xa;
			y += ya;
		}
	}

	protected boolean collisionWithTile(int x, int y) {

		if (island != null) {
			if (movingDir == 1) // right
				return island.getTile(x / Tile.width + 1, y / Tile.width + 1).isSolid();
			if (movingDir == 2) // down
				return island.getTile(x / Tile.width, (y + Tile.width) / Tile.width + 1).isSolid();
			if (movingDir == 3) // left
				return island.getTile((x - 1) / Tile.width, y / Tile.width + 1).isSolid() || x - 1 < 0;
			if (movingDir == 4) // up
				return island.getTile(x / Tile.width, (y - 1) / Tile.width + 1).isSolid() || (y + Tile.width) - 1 < 0;
			return false;
		}
		return false;
	}

	@Override
	public abstract void tick();

}
