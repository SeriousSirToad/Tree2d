package kingery.game.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.gfx.Assets;
import kingery.game.islands.tiles.Tile;

public class Item {

	public static Item[] items = new Item[256];
	public static final Item woodItem = new Item(Assets.WOOD, "Wood", 1);

	public static final int ITEMWIDTH = 8, ITEMHEIGHT = 16;
	boolean pickedUp = false;

	protected Engine e;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	protected int x, y, count;

	public Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;

		items[id] = this;
	}

	public void update() {

	}

	public void render(Graphics g) {
		if (e == null)
			return;
		render(g, (int) (x - e.cameraX), (int) (y - e.cameraY));
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, ITEMWIDTH * Tile.scale, ITEMHEIGHT * Tile.scale, null);
	}

	public Item createNew(int x, int y) {
		Item i = new Item(texture, name, id);
		i.setPostion(x, y);
		return i;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setPostion(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
