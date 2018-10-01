package kingery.game.entities.flora;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.entities.Entity;
import kingery.game.gfx.Animation;
import kingery.game.gfx.Assets;
import kingery.game.gfx.Camera;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class Tree extends Entity {

	public static final int SMALL = 0;
	public static final int GROWN = 1;
	Animation eyebrows;
	public int growthState;

	public Tree(int x, int y, int growthState, Engine e, Island island) {
		super(x, y, "tree", false, Assets.treeImages[growthState], e, island);
		this.growthState = growthState;
		eyebrows = new Animation((short) 2, (byte) 120,
				new BufferedImage[] { Assets.treeImages[1], Assets.treeImages[2] });

	}

	@Override
	public void update() {
		eyebrows.update();
	}

	public void render(Graphics g) {
		if (growthState == SMALL) {
			g.drawImage(entityImage, x - Camera.x(), y - Camera.y(), width, height, null);
		} else {
			g.drawImage(eyebrows.animate(), x - Camera.x(), y - Camera.y(), width, height, null);
		}
	}

}
