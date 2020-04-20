package kingery.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.engine.GameState;
import kingery.game.gfx.Camera;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public abstract class Entity {

	public String name;

	public int x, y, width, height;

	public Color EntityColor = Color.black;
	public Color defaultColor = EntityColor;
	public BufferedImage entityImage;
	public boolean moving = false;

	public String name_short;

	protected int movingDir = 0;
	protected int collidedDir = 0;
	public boolean dead;

	public Engine e;
	protected Island island;

	private boolean isMob;

	public Entity(int x, int y, String name_short, boolean isMob, BufferedImage entityImage, Engine e, Island island) {

		this.x = x;
		this.y = y;
		this.name_short = name_short;
		this.e = e;
		this.name_short = name_short;
		this.isMob = isMob;
		this.entityImage = entityImage;

		width = entityImage.getWidth();
		height = entityImage.getHeight();

		island.entities.add(this);
		System.out.println(island.imagePath);

	}

	public int hpCount;
	public boolean hpCounting;

	public boolean isMob() {
		return isMob;
	}

	public void changeIsland(Island i) {
		GameState.currentLevel = i;
		island.entities.remove(this);
		island = GameState.currentLevel;
		island.entities.add(this);
	}

	public abstract void tick();

	public void render(Graphics g) {
		if (Camera.contains(this)) {
			g.drawImage(entityImage, x - Camera.x(), y - Camera.y(), width, height, null);
		}
	}

}
