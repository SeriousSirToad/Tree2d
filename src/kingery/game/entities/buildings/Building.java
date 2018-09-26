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

		dx = x + (width / 2 - (1 * Tile.width));
		dy = y + (height - ((1 * Tile.width) / 2));
		dw = 2 * Tile.width;
		dh = 1 * Tile.width;
		this.id = id;
		this.island = island;

		dCollider = new Rectangle(dx, dy, dw, dh);

	}

	boolean canOpen = false;

	@Override
	public void update() {

		if (dCollider.intersects(Engine.p.zoneCheck)) {
			
			if (e.input.f.isPressed()) {
				canOpen = true;
			}

			if (canOpen && !e.input.f.isPressed()) {
				canOpen = false;
				inside = true;
			}

		}

	}

	public void render(Graphics g) {
		g.drawImage(entityImage, x - Camera.x(), y - Camera.y(), width, height, null);
	}

}
