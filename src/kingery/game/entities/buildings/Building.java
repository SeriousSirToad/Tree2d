package kingery.game.entities.buildings;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.entities.Entity;
import kingery.game.gfx.Camera;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class Building extends Entity {

	private Rectangle dCollider;
	public Island interior;
	int dx, dy, dw, dh;
	int id;

	public static Island interior_0;
	public static Island interior_1;

	public Building(int id, int x, int y, BufferedImage entityImage, Engine e, Island island) {

		super(x, y, null, false, entityImage, e, island);

		dx = x + (width / 2 - (1 * Tile.width));
		dy = y + (height - ((1 * Tile.width) / 2));
		dw = 2 * Tile.width;
		dh = 1 * Tile.width;
		this.id = id;
		this.island = island;

		dCollider = new Rectangle(dx, dy, dw, dh);

		initThatShitRn();

	}

	boolean canOpen = false;

	@Override
	public void update() {
		
		if(island == null) {
			initThatShitRn();
		}

		if (dCollider.intersects(Engine.p.zoneCheck)) {
			
			if (e.input.f.isPressed()) {
				canOpen = true;
			}

			if (canOpen && !e.input.f.isPressed()) {
				Engine.p.changeIsland(interior);
				Engine.p.x = (interior.width / 2) * Tile.width;
				Engine.p.y = (interior.height * Tile.width) - (4 * Tile.width);
				canOpen = false;
			}

		}

	}

	public void render(Graphics g) {
		g.drawImage(entityImage, x - Camera.x(), y - Camera.y(), width, height, null);
		g.drawRect(dx - Camera.x(), dy - Camera.y(), dw, dh);
	}
	
	public void initThatShitRn() {

		if (interior == null) {
			switch (id) {

			case 0:
				interior = interior_0;
				interior.leftI = island;
				interior.rightI = island;
				
			//case 1:
			//	interior = interior_1;
				//interior.leftI = island;
				//interior.rightI = interior.leftI;
			default:
				interior = interior_0;
				interior.leftI = island;
				interior.rightI = island;
			}
		}
	}

}
