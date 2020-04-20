package kingery.game.entities.buildings;

import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.entities.Entity;
import kingery.game.islands.Island;

public abstract class Building extends Entity {

	public Island interior;
	int dx, dy, dw, dh;
	int id;
	public boolean inside = false;
	public String bldg_name;
	public String bldg_desc;

	public Building(int id, int x, int y, BufferedImage entityImage, Engine e, Island island) {

		super(x, y, null, false, entityImage, e, island);
		this.id = id;
		this.island = island;

	}

	boolean canOpen = false;

	@Override
	public void tick() {


	}

}
