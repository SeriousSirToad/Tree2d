package kingery.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.engine.InputHandler;
import kingery.game.entities.npcs.Lumberjack;
import kingery.game.gfx.Animation;
import kingery.game.gfx.Assets;
import kingery.game.gfx.Camera;
import kingery.game.gfx.Colour;
import kingery.game.gfx.SpriteSheet;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class Player extends Mob {

	public InputHandler input;

	public Rectangle zoneCheck;

	static int[] colors = { 0xFF7F7F7F, 0xFF3F3F3F, 0xFF1F1F1F, 0xFF9F9F9F };
	static int[] newColors = { Lumberjack.RED, Lumberjack.BLUE, 0xFF9F9F9F,
			0xFFF7D7C4 };

	private int movingDir = 0;
	public boolean moving = false;

	public Animation walkR;
	public Animation walkU;

	public Player(String username, int x, int y, InputHandler input, Engine e,
			Island is) {

		super(x, y, "player", Colour
				.fixYoSelf(colors, newColors, Assets.PLAYER), e, is);

		BufferedImage[] someImages = new BufferedImage[6];
		for (int i = 0; i < someImages.length; i++) {
			someImages[i] = Colour.fixYoSelf(colors, newColors,
					SpriteSheet.getImage(8 + i * 8, 584, 8, 16));
		}
		walkR = new Animation((short) someImages.length, (byte) 8, someImages);

		BufferedImage[] otherImages = new BufferedImage[2];
		for (int i = 0; i < otherImages.length; i++) {
			otherImages[i] = Colour.fixYoSelf(colors, newColors,
					SpriteSheet.getImage(64 + i * 8, 584, 8, 16));
		}
		walkU = new Animation((short) otherImages.length, (byte) (12),
				otherImages);
		System.out.println(walkU.frames.length);

		name = username;
		EntityColor = Color.orange;
		defaultColor = EntityColor;
		this.input = input;

		zoneCheck = new Rectangle(x, y, width, height);

		collider.x = 1 * Tile.scale;
		collider.y = 10 * Tile.scale;
		collider.width = (int) (5.5 * Tile.scale);
		collider.height = (6 * Tile.scale);

	}

	public boolean shouldBeMoving = false;

	int moveSwitch = 0;
	int moveInt = 2;

	@Override
	public void update() {

		inventory.update();

		zoneCheck.x = x;
		zoneCheck.y = y;

		if (moveSwitch > moveInt) {
			moveSwitch = 0;
		}

		walkR.update();
		walkU.update();

		xa = 0;
		ya = 0;

		if (input.w.isPressed()) {
			ya = -speed;
			movingDir = 2;
		}
		if (input.s.isPressed()) {
			ya = speed;
			movingDir = 2;
		}
		if (input.a.isPressed()) {
			xa = -speed;
			movingDir = 3;
		}
		if (input.d.isPressed()) {
			xa = speed;
			movingDir = 1;
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
			movingDir = 0;
		}

		doIslandStuff();

	}

	public void doIslandStuff() {
		if (shouldExit((x + xa + collider.x + collider.width) / Tile.width,
				(y + collider.y) / Tile.width)) {
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
						this.x = island.width - ((x + 1) * Tile.width);
						break thid;
					}
				}
			}

		} else if (shouldExit((x + xa + collider.x) / Tile.width,
				(y + collider.y) / Tile.width)) {
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
						this.x = (x - 2) * Tile.width;
					}
				}
			} // y + ya + collider.y + collider.height
		} else if (shouldExit((x + collider.x) / Tile.width,
				(y + ya + collider.y) / Tile.width)) {
			if ((y + collider.y) / Tile.width < island.height / 2) {
				e.island = island.rightI;
				System.out.println(e.island.imagePath);
			}

			island.entities.remove(this);
			island = e.island;
			island.entities.add(this);

			for (int x = 0; x < e.island.width; x++) {
				for (int y = 0; y < e.island.height; y++) {
					if (island.getTile(x, y).getId() == 8) {
						this.x = (y - 1) * Tile.width;
					}
				}
			}
		} else if (shouldExit((x + collider.x) / Tile.width, (y + ya
				+ collider.y + collider.height)
				/ Tile.width)) {
			if ((y + collider.y) / Tile.width > island.height / 2) {
				e.island = island.rightI;
				// System.out.println(e.island.imagePath);
			}

			island.entities.remove(this);
			island = e.island;
			island.entities.add(this);

			for (int x = 0; x < e.island.width; x++) {
				for (int y = 0; y < e.island.height; y++) {
					if (island.getTile(x, y).getId() == 8) {
						this.y = (y + 2) * Tile.width;
					}
				}
			}
		}
	}

	public void changeIsland(Island i) {
		e.island = i;
		island.entities.remove(this);
		island = e.island;
		island.entities.add(this);
	}

	@Override
	public void render(Graphics g) {

		if (moving && movingDir == 1) {
			g.drawImage(walkR.animate(), x - Camera.x(), y - Camera.y(), width,
					height, null);
		} else if (moving && movingDir == 3) {
			g.drawImage(walkR.animate(), x - Camera.x() + width,
					y - Camera.y(), -width, height, null);
		} else if (moving && movingDir == 2) {
			g.drawImage(walkU.animate(), x - Camera.x(), y - Camera.y(), width,
					height, null);
		} else {
			g.drawImage(entityImage, x - Camera.x(), y - Camera.y(), width,
					height, null);
		}

	}

	public byte speed() {
		return speed;
	}

	int moveCount = 0;

	public void updateName(String name) {

		this.name = name;

	}

}
