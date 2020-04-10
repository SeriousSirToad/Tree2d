package kingery.game.entities.buildings;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.entities.Entity;
import kingery.game.gfx.Camera;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;
import kingery.ui.BuildingWindow;

public abstract class Building extends Entity {

	private Rectangle dCollider;
	public BuildingWindow interior;
	int dx, dy, dw, dh;
	int id;
	public boolean inside = false;
	public String bldg_name;
	public String bldg_desc;

	public Building(int id, int x, int y, BufferedImage entityImage, Engine e, Island island) {

		super(x, y, null, false, entityImage, e, island);

		dx = x + (width / 2 - (1));
		dy = y + (height - ((1) / 2));
		dw = 2 * Tile.width;
		dh = Tile.width;
		this.id = id;
		this.island = island;

		dCollider = new Rectangle(dx, dy, dw, dh);

	}

	boolean canOpen = false;

	@Override
	public void update() {

		if (dCollider.intersects(Engine.p.collider)) {
			
			if (e.input.f.isPressed()) {
				canOpen = true;
			}

			if (canOpen && !e.input.f.isPressed()) {
				canOpen = false;
				inside = true;
			}

		}

	}

}
