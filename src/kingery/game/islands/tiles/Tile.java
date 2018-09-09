package kingery.game.islands.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.gfx.SpriteSheet;

public abstract class Tile {

	public static final Tile[] tiles = new Tile[256];
	public static final Tile GRASS = new BasicTile(1, 0, 0, 0xFF00FF00);
	public static final Tile VOID = new BasicSolidTile(0, 40, 0, 0xFF000000);
	public static final Tile STONE = new BasicTile(2, 0, 24, 0xFF7F7F7F);
	public static final Tile STONE_SOLID = new BasicSolidTile(3, 8, 24, 0xFF444444);
	public static final Tile WOOD = new BasicTile(4, 0, 32, 0xFFFFFF00);
	public static final Tile WOOD_SOLID = new BasicSolidTile(5, 8, 32, 0xFF5F5F00);
	public static final Tile WATER = new BasicSolidTile(6, 24, 0, 0xFF0000FF);
	public static final Tile FLORAL_GRASS = new BasicTile(7, 8, 0, 0xFF227200);
	public static final Tile FLORAL_GRASS1 = new BasicTile(9, 0, 8, 0xFF00FF70);
	public static final Tile FLORAL_GRASS2 = new BasicTile(10, 8, 8, 0xFF00E500);
	public static final Tile EXITTILE = new BasicTile(8, 0, 24, 0xFF8F8F8F);
	public static final Tile COUNTER = new BasicSolidTile(11, 0, 40, 0xFF888264);
	public static final Tile COUNTER2 = new BasicSolidTile(12, 8, 40, 0xFF888F64);
	public static final Tile BRICK_ROAD = new BasicTile(13, 16, 24, 0xFF7F0000);

	protected boolean isSolid, isEmitter, isRandom = false;
	protected byte id;
	private int levelColor;
	public final static int scale = (int)(2 * Engine.SCALE);
	public static final byte textureWidth = 8;
	public static final int width = textureWidth * scale;
	public BufferedImage tileImage;
	public Rectangle collider;
	public int defX = 0, defY = 0;

	public Tile(int id, boolean isSolid, boolean isEmitter, int SSX, int SSY, int levelColour) {

		this.id = (byte) id;
		this.isSolid = isSolid;
		this.isEmitter = isEmitter;
		tiles[id] = this;
		this.levelColor = levelColour;
		tileImage = SpriteSheet.getImage(SSX, SSY, textureWidth, textureWidth);
		collider = new Rectangle(0, 0, width, width);

	}

	public void renderTile(Graphics g, int x, int y) {
		
		g.drawImage(tileImage, x, y, width, width, null);

		collider.x = x;
		collider.y = y;

	}

	public boolean isSolid() {
		return isSolid;
	}

	public int getId() {
		return id;
	}

	public int getLevelColour() {
		return levelColor;
	}

}
