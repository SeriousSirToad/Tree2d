package kingery.game.islands;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.imageio.ImageIO;

import kingery.game.engine.GameState;
import kingery.game.entities.Entity;
import kingery.game.entities.buildings.Door;
import kingery.game.islands.tiles.Tile;

public class Island {

	public BufferedImage levelImage;
	public BufferedImage renderImage;
	public ArrayList<Entity> entities = new ArrayList<>();
	public ArrayList<Door> doors = new ArrayList<>();
	boolean hasCycledA = false;
	boolean hasCycledB = false;
	private int[][] tiles;
	public int width;
	public int height;
	public String imagePath;
	private BufferedImage image;

	public static Island Utopia;
	public static Island_Start Start;

	private Comparator<Entity> entitySorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if (a.y + a.height < b.y + b.height)
				return -1;
			return 1;
		}
	};

	public Island(String imagePath) {
		if (imagePath != null) {
			this.imagePath = imagePath;
			this.loadLevelFromFile();
		} else {
			this.width = 64;
			this.height = 64;
			tiles = new int[width][height];
			this.GenerateIsland();
		}

	}

	private void loadLevelFromFile() {
		try {
			this.image = ImageIO.read(new File(imagePath));
			this.width = this.image.getWidth();
			this.height = this.image.getHeight();
			tiles = new int[width][height];
			this.loadTiles();
			renderImage = new BufferedImage(width * Tile.width, height * Tile.width, BufferedImage.TYPE_INT_RGB);
			Graphics g = renderImage.getGraphics();
			renderTile(g);
			g.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadTiles() {
		int[] tileColours = this.image.getRGB(0, 0, width, height, null, 0, width);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileCheck: for (Tile t : Tile.tiles) {
					if (t != null && t.getLevelColour() == tileColours[x + y * width]) {
						this.tiles[x][y] = t.getId();
						break tileCheck;
					}
				}
			}
		}
	}

	int xOffset, yOffset;

	public Tile getTile(int x, int y) {
		if (x < 0 || x > width - 1 || y < 0 || y > height - 1)
			return Tile.VOID;
		return Tile.tiles[tiles[x][y]];
	}

	public void renderTile(Graphics g) {

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				getTile(x, y).renderTile(g, x * Tile.width, y * Tile.width);
			}
		}

	}

	public void update() {

		if (GameState.time >= 24) {
			GameState.time = (GameState.time - 24);
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}

		entities.sort(entitySorter);

	}

	public void render(Graphics2D g) {

		g.drawImage(renderImage, -GameState.camera.x(), -GameState.camera.y(), null);

		for (Entity f : entities) {
			if (GameState.camera.contains(f))
				f.render(g);
		}
		g.setColor(Color.BLACK);

	}

	public void init() {

	}

	public static final int MORNING = 1, EARLY_MORNING = 0, AFTERNOON = 2, EVENING = 3;

	public void GenerateIsland() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (x * y % 10 < 8) {
					tiles[x][y] = Tile.GRASS.getId();
				} else {
					tiles[x][y] = Tile.STONE.getId();
				}
			}
		}
	}

	public int scaledHeight() {
		return height * Tile.width * Tile.scale;
	}
	
	public int scaledWidth() {
		return width * Tile.width * Tile.scale;
	}

}
