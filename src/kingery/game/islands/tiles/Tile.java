package kingery.game.islands.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.gfx.Assets;

public abstract class Tile {

	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new BasicSolidTile(0, 80, 0, 0xFF000000);
	public static final Tile GRASS = new BasicTile(1, 0, 0, 0xFF00FF00);
	public static final Tile STONE = new BasicTile(2, 0, 48, 0xFF7F7F7F);
	public static final Tile STONE_SOLID = new BasicSolidTile(3, 16, 48, 0xFF444444);
	public static final Tile WOOD = new BasicTile(4, 0, 64, 0xFFAB9584);
	public static final Tile WATER = new BasicSolidTile(6, 48, 0, 0xFF0000FF);
	public static final Tile FLORAL_GRASS = new BasicTile(7, 16, 0, 0xFF227200);
	public static final Tile FLORAL_GRASS1 = new BasicTile(8, 0, 16, 0xFF00FF70);
	public static final Tile FLORAL_GRASS2 = new BasicTile(9, 16, 16, 0xFF00E500);
	public static final Tile BRICK_ROAD = new BasicTile(10, 32, 48, 0xFF7F0000);
	public static final Tile SIDEWALK = new BasicTile(12, 48, 48, 0xFF9F9F9F);
	public static final Tile GRASS_PLAIN = new BasicTile(13, 32, 0, 0xFF009900);

	protected boolean isSolid, isEmitter, isRandom = false;
	protected byte id;
	private int levelColor;
	public final static int scale = 4;
	public static final int width = 16;
	public BufferedImage tileImage;
	public Rectangle collider;
	public int defX = 0, defY = 0;

	public Tile(int id, boolean isSolid, boolean isEmitter, int SSX, int SSY, int levelColour) {

		this.id = (byte) id;
		this.isSolid = isSolid;
		this.isEmitter = isEmitter;
		tiles[id] = this;
		this.levelColor = levelColour;
		tileImage = Assets.sheet.getImage(SSX, SSY, width, width);

	}

	public void renderTile(Graphics g, int x, int y) {

		g.drawImage(tileImage, x, y, width, width, null);

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
