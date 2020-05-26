package kingery.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.GameState;
import kingery.game.islands.Island;

public abstract class Entity {

	public String name;

	public int x, y, width, height;

	public Color EntityColor = Color.black;
	public Color defaultColor = EntityColor;
	public BufferedImage entityImage;
	public boolean moving = false;
	public Rectangle collider;

	public String name_short;

	protected int movingDir = 0;
	protected int collidedDir = 0;
	public boolean dead;

	protected Island island;

	private boolean isMob;

	public Entity(int x, int y, String name_short, boolean isMob, BufferedImage entityImage, Island island) {

		this.x = x;
		this.y = y;
		this.name_short = name_short;
		this.isMob = isMob;
		this.entityImage = entityImage;

		if (entityImage != null) {
			width = entityImage.getWidth();
			height = entityImage.getHeight();
		}

		island.entities.add(this);

		collider = new Rectangle(width, height);
	}

	public int hpCount;
	public boolean hpCounting;

	public boolean isMob() {
		return isMob;
	}

	public void changeIsland(Island i) {
		island.entities.remove(this);
		island = i;
		island.entities.add(this);
	}

	public abstract void tick();
	
	public void makeCollider(int width, int height) {
		collider = new Rectangle(width, height);
	}

	public void render(Graphics g) {
		if (GameState.camera.contains(this)) {
			g.drawImage(entityImage, x - GameState.camera.x(), y - GameState.camera.y(), width, height, null);
		}
	}

	public Island getLevel() {
		return island;
	}

}
