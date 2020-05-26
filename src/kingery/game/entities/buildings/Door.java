package kingery.game.entities.buildings;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import kingery.game.entities.Entity;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class Door extends Entity {
	public Island nativeLevel;
	public Island level;
	public Point tpLocation;
	public Point location;
	public int width, height;
	Rectangle door;

	public Door(Island nativeLevel, Rectangle door, Island level, Point tpLocation) {

		super(door.x, door.y, "door", false, null, nativeLevel);
		this.location = door.getLocation();

		width = door.width;
		height = door.height;
		this.level = level;
		this.tpLocation = tpLocation;
		this.nativeLevel = nativeLevel;
		nativeLevel.doors.add(this);
		this.door = door;
		System.out.println("door " + location);

	}

	public void tick() {
		for (int i = 0; i < nativeLevel.entities.size(); i++) {
			Entity e = nativeLevel.entities.get(i);
			if (e.isMob()) {
				if (door.intersects(new Rectangle(e.x, e.y + e.height / 2, e.width, e.height / 2))) {
					e.changeIsland(level);
					e.x = tpLocation.x * Tile.width;
					e.y = tpLocation.y * Tile.width;
				}
			}
		}
	}

	public void render(Graphics g) {
		return;
	}

}
