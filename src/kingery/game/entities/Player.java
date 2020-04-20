package kingery.game.entities;

import java.awt.Color;
import java.awt.Graphics;

import kingery.game.engine.Engine;
import kingery.game.engine.GameState;
import kingery.game.engine.InputHandler;
import kingery.game.entities.npcs.Lumberjack;
import kingery.game.gfx.Animation;
import kingery.game.gfx.Assets;
import kingery.game.gfx.Camera;
import kingery.game.gfx.Colour;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class Player extends Mob {

	public InputHandler input;

	private static int[] colors = { 0xFF7F7F7F, 0xFF3F3F3F, 0xFF1F1F1F, 0xFF9F9F9F, 0xFF232323, 0xFF424242 };
	private static int[] newColors = { Lumberjack.PURPLE, Lumberjack.BLUE, 0xFF000099, 0xFFF7D7C4, 0xFF442109,
			0xFF8B4513 };

	public int money = 100;
	public boolean moving = false;

	public Animation walkR = Assets.PLAYER_HORIZONTAL;
	public Animation walkU = Assets.PLAYER_UP;
	public Animation walkD = Assets.PLAYER_DOWN;

	public Player(String username, int x, int y, InputHandler input, Engine e, Island is) {

		super(x, y, "player", Colour.fixYoSelf(colors, newColors, Assets.PLAYER), e, is);
		name = username;
		EntityColor = Color.orange;
		defaultColor = EntityColor;
		this.input = input;
	}

	@Override
	public void tick() {

		if (x % Tile.width == 0 && y % Tile.width == 0) {
			xa = 0;
			ya = 0;
			if (input.w.isPressed()) {
				ya = -speed;
				movingDir = 4;
			} else if (input.s.isPressed()) {
				ya = speed;
				movingDir = 2;
			} else if (input.a.isPressed()) {
				xa = -speed;
				movingDir = 3;
			} else if (input.d.isPressed()) {
				xa = speed;
				movingDir = 1;
			}
		}

		if (xa != 0 || ya != 0) {
			move();
			moving = true;
		} else {
			moving = false;
		}

		if (island == null)
			island = GameState.currentLevel;

	}


	@Override
	public void render(Graphics g) {

		if (moving) {
			if (movingDir == 1) {
				g.drawImage(walkR.animate(), x - Camera.x(), y - Camera.y(), width, height, null);
			} else if (movingDir == 3) {
				g.drawImage(walkR.animate(), x - Camera.x() + width, y - Camera.y(), -width, height, null);
			} else if (movingDir == 2) {
				g.drawImage(walkD.animate(), x - Camera.x(), y - Camera.y(), width, height, null);
			} else {
				g.drawImage(walkU.animate(), x - Camera.x(), y - Camera.y(), width, height, null);
			}
		} else {
			// System.out.println("penis");
			switch (movingDir) {
			case 1:
				g.drawImage(walkR.currentFrame, x - Camera.x(), y - Camera.y(), width, height, null);
				walkR.reset();
				break;
			case 3:
				g.drawImage(walkR.currentFrame, x - Camera.x() + width, y - Camera.y(), -width, height, null);
				walkR.reset();
				break;
			case 2:
				g.drawImage(walkD.currentFrame, x - Camera.x(), y - Camera.y(), width, height, null);
				walkD.reset();
				break;
			case 4:
				g.drawImage(walkU.currentFrame, x - Camera.x(), y - Camera.y(), width, height, null);
				walkU.reset();
				break;
			default:
				g.drawImage(walkR.currentFrame, x - Camera.x(), y - Camera.y(), width, height, null);
			}
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
