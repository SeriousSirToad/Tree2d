package kingery.game.entities.buildings;

import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.entities.Entity;
import kingery.game.gfx.Assets;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class Building extends Entity{

	public Building(int x, int y, boolean isMob, BufferedImage entityImage, Engine e,
			Island island) {
		super(x, y, null, isMob, entityImage, e, island);
		
		width = width * Tile.scale;
		height = height * Tile.scale;
		
		System.out.print(island.imagePath);
		
	}

	@Override
	public void update() {
		
	}
	
	
	
}
