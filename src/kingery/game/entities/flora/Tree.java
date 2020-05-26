package kingery.game.entities.flora;

import java.awt.Graphics;

import kingery.game.engine.GameState;
import kingery.game.entities.Entity;
import kingery.game.gfx.Animation;
import kingery.game.gfx.Assets;
import kingery.game.islands.Island;

public class Tree extends Entity {

	int growthstate = 0;

	Animation growCycle;

	public Tree(int x, int y, int growthState, Island island) {
		super(x, y, "tree", false, Assets.treeImages[0], island);
		this.growthstate = growthState;

	}

	public void render(Graphics g) {
		g.drawImage(growCycle.frames[growthstate], x - GameState.camera.x(), y - GameState.camera.y(), width, height, null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
