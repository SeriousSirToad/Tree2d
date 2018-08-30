package kingery.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.engine.InputHandler;
import kingery.game.gfx.Animation;
import kingery.game.gfx.Assets;
import kingery.game.gfx.Camera;
import kingery.game.gfx.SpriteSheet;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class Player extends Mob {

	public InputHandler input;
	public int movingDir;

	public Rectangle zoneCheck;

	public boolean moving = false;
	public boolean running = false;
	public boolean goingRight = false;
	public boolean goingLeft = false;
	public int speedMultiplier = 1;
	int numSteps;

	public Animation walkR;

	public Player(String username, int x, int y, InputHandler input, Engine e, Island is) {

		super(x, y, "player", Assets.PLAYER, e, is);

		BufferedImage[] someImages = new BufferedImage[2];

		for (int i = 0; i < 2; i++) {
			someImages[i] = SpriteSheet.getImage(8 + i * 8, 584, 8, 16);
		}

		walkR = new Animation((short) 2, (byte) 8, someImages);

		name = username;
		EntityColor = Color.orange;
		defaultColor = EntityColor;
		this.input = input;

		zoneCheck = new Rectangle(x, y, width, height);

		collider.x = 1 * Tile.scale;
		collider.y = 12 * Tile.scale;
		collider.width = 5 * Tile.scale;
		collider.height = 4 * Tile.scale;

	}

	public boolean shouldBeMoving = false;

	int moveSwitch = 0;
	int moveInt = 2;

	@Override
	public void update() {

		zoneCheck.x = x;
		zoneCheck.y = y;

		if (moveSwitch > moveInt) {
			moveSwitch = 0;
		}

		walkR.update();

		xa = 0;
		ya = 0;

		if (input.w.isPressed())
			ya = -speed;
		if (input.s.isPressed())
			ya = speed;
		if (input.a.isPressed()) {
			xa = -speed;
			goingLeft = true;
			goingRight = false;
		}
		if (input.d.isPressed()) {
			xa = speed;
			goingLeft = false;
			goingRight = true;
		}
		if (xa != 0 || ya != 0) {
			move();
			moving = true;
		} else {
			moving = false;
		}

		if (island == null)
			island = e.island;

		if (moving) {
			shouldBeMoving = true;
		} else {
			shouldBeMoving = false;
		}

		if (shouldExit((x + xa + collider.x + collider.width) / Tile.width, (y + collider.y) / Tile.width)) {
			if ((x + collider.x) / Tile.width > island.width / 2) {
				e.island = island.rightI;
				System.out.println(e.island.imagePath);
			}

			island.entities.remove(this);
			island = e.island;
			island.entities.add(this);

			thid: for (int x = 0; x < island.width; x++) {
				for (int y = 0; y < island.height; y++) {
					if (island.getTile(x, y).getId() == 8) {
						this.x = island.width - ((x + 2) * Tile.width);
						break thid;
					}
				}
			}

		} else if (shouldExit((x + xa + collider.x) / Tile.width, (y + collider.y) / Tile.width)) {
			if ((x + collider.x) / Tile.width < island.width / 2) {
				e.island = island.leftI;
				System.out.println(e.island.imagePath);
			}

			island.entities.remove(this);
			island = e.island;
			island.entities.add(this);

			for (int x = 0; x < e.island.width; x++) {
				for (int y = 0; y < e.island.height; y++) {
					if (island.getTile(x, y).getId() == 8) {
						this.x = (x - 1) * Tile.width;
					}
				}
			}
		}

	}

	@Override
	public void render(Graphics g) {

		if (moving && goingRight) {
			g.drawImage(walkR.animate(), x - Camera.x(), y - Camera.y(), width, height, null);
		} else if (moving && goingLeft) {
			g.drawImage(walkR.animate(), x - Camera.x() + width, y - Camera.y(), -width, height, null);
		} else if (!moving && goingLeft) {
			g.drawImage(entityImage, x - Camera.x() + width, y - Camera.y(), -width, height, null);
		} else {
			g.drawImage(entityImage, x - Camera.x(), y - Camera.y(), width, height, null);
		}

	}

	public int xMin = 0;
	public int xMax = 1;
	public int yMin = 0;
	public int yMax = 1;

	public byte speed() {
		return speed;
	}

	int moveCount = 0;

	public void updateName(String name) {

		this.name = name;

	}

}
