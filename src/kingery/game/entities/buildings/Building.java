package kingery.game.entities.buildings;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.entities.Entity;
import kingery.game.islands.Island;
import kingery.game.islands.interior.interior_generic;
import kingery.game.islands.tiles.Tile;

public abstract class Building extends Entity {

	public Island interior;
	int dx, dy, dw, dh;
	public boolean inside = false;
	public String bldg_name;
	public String bldg_desc;
	public Door door;
	public Door otherDoor;

	public Building(int x, int y, BufferedImage entityImage, Island island) {

		super(x, y, null, false, entityImage, island);
		this.island = island;
		this.interior = new interior_generic();
		makeStandardDoor(32, 1);

	}

	@Override
	public void tick() {

	}

	protected void makeStandardDoor(int x, int y) {
		Rectangle doorRect = new Rectangle(this.x + x, this.y + this.height - y, 32, 1);
		Rectangle door2 = new Rectangle((interior.width * Tile.width / 2) - 16, interior.height * Tile.width - 1, 32, 1);
		door = new Door(island, doorRect, interior, new Point(interior.width / 2, interior.height - 3));
		otherDoor = new Door(interior, door2, island,
				new Point((this.x + this.width / 2) / Tile.width, (this.y + this.height) / Tile.width - 1));
		System.out.println(" " + otherDoor.location);

	}

}
