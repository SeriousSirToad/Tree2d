package kingery.game.entities.buildings;

import java.awt.Point;
import java.awt.Rectangle;

import kingery.game.entities.Entity;
import kingery.game.gfx.Camera;
import kingery.game.islands.Island;

public class Door {
	Island nativeLevel;
	Island level;
	Point tpLocation;
	Point location;
	int width, height;
	Rectangle door;

	public Door(Island nativeLevel, Rectangle door, Point tpLocation, Island level) {

		this.location = door.getLocation();

		width = door.width;
		height = door.height;
		this.level = level;
		this.tpLocation = tpLocation;
		this.nativeLevel = nativeLevel;
		nativeLevel.doors.add(this);
		this.door = door;

	}

	public void tick() {
		if (Camera.contains(door)) {
			for (Entity e : nativeLevel.entities) {
				e.changeIsland(level);
				e.x = (int) tpLocation.getX();
				e.y = (int) tpLocation.getY();
			}
		}
	}

}
