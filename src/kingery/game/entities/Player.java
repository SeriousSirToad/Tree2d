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

public class Player extends Mob {

	public InputHandler input;
	public int movingDir;

	public Rectangle zoneCheck;
	
	public boolean moving = false;
	public boolean running = false;
	public boolean goingRight = false;
	public boolean goingLeft = false;
	public int speedMultiplier = 1;
	private int scale = 2;
	int numSteps;

	public Animation walkR;

	public Player(String username, int x, int y, InputHandler input, Engine e) {

		super(x, y, "player", Assets.PLAYER, e, e.island);

		BufferedImage[] someImages = new BufferedImage[12];

		for (int i = 0; i < 12; i++) {
			someImages[i] = SpriteSheet.getImage(32 + i * 32, 536, 32, 64);
		}

		walkR = new Animation((short) 12, (byte) 2, someImages);

		name = username;
		EntityColor = Color.orange;
		defaultColor = EntityColor;
		this.input = input;

		zoneCheck = new Rectangle(x, y, width, height);
		
		collider.x = 9;
		collider.y = 43;
		collider.width = 16;
		collider.height = 22;

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

	}

	@Override
	public void render(Graphics g) {

		if (moving && goingRight) {
			g.drawImage(walkR.animate(), x - Camera.x(), y - Camera.y(), width, height, null);
		} else if (moving && goingLeft) {
			g.drawImage(walkR.animate(), x - Camera.x() + width, y - Camera.y(), -width, height, null);
		} else {
			g.drawImage(entityImage, x - Camera.x(), y - Camera.y(), width, height, null);
			Engine.p.walkR.reset();
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
