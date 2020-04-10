package kingery.game.entities.flora;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.entities.Entity;
import kingery.game.gfx.Animation;
import kingery.game.gfx.Assets;
import kingery.game.gfx.Camera;
import kingery.game.islands.Island;

public class Tree extends Entity {

	public enum GrowthState{
		SMALL,
		GROWN
	}
	
	Animation eyebrows;
	GrowthState growthState;

	public Tree(int x, int y, GrowthState growthState, Engine e, Island island) {
		super(x, y, "tree", false, Assets.treeImages[0], e, island);
		this.growthState = growthState;

	}

	public void render(Graphics g) {
		if (growthState == GrowthState.SMALL) {
			g.drawImage(entityImage, x - Camera.x(), y - Camera.y(), width, height, null);
		} else {
			g.drawImage(eyebrows.animate(), x - Camera.x(), y - Camera.y(), width, height, null);
		}
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
