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

import kingery.game.engine.Engine;
import kingery.game.engine.GameState;
import kingery.game.entities.Entity;
import kingery.game.gfx.Camera;
import kingery.game.gfx.lighting.BigLight;
import kingery.game.islands.tiles.Tile;

public class Island {

	public BufferedImage levelImage;
	public ArrayList<Entity> entities = new ArrayList<>();
	boolean hasCycledA = false;
	boolean hasCycledB = false;
	private int[][] tiles;
	public int width;
	public int height;
	public String imagePath;
	private BufferedImage image;
	protected BigLight sun;
	//

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

	public Island(String imagePath, Engine e) {
		if (imagePath != null) {
			this.imagePath = imagePath;
			this.loadLevelFromFile();
		} else {
			this.width = 64;
			this.height = 64;
			tiles = new int[width][height];
			this.GenerateIsland();
		}

		sun = new BigLight();
	}

	private void loadLevelFromFile() {
		try {
			this.image = ImageIO.read(new File(imagePath));
			this.width = this.image.getWidth();
			this.height = this.image.getHeight();
			tiles = new int[width][height];
			this.loadTiles();
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
		if (x < 0 || x > width || 0 > y || y > height)
			return Tile.VOID;
		return Tile.tiles[tiles[x][y]];
	}

	public void renderTile(Graphics g) {

		int xMin = Math.max(0, Camera.x() / Tile.width);
		int xMax = Math.min(width, (Camera.x() + Camera.width) / Tile.width + 1);
		int yMin = Math.max(0, Camera.y() / Tile.width);
		int yMax = Math.min(height, (Camera.y() + Camera.height) / Tile.width + 1);

		for (int y = yMin; y < yMax; y++) {
			for (int x = xMin; x < xMax; x++) {
				getTile(x, y).renderTile(g, x * Tile.width - Camera.x(), y * Tile.width - Camera.y());
			}
		}

	}

	public void update() {

		if (GameState.time >= 24) {
			GameState.time = (GameState.time - 24);
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}

		if (timeIndex() == EVENING) {
			sun.colour = BigLight.evening;
		} else if (timeIndex() == AFTERNOON) {
			sun.colour = BigLight.afternoon;
		} else if (timeIndex() == MORNING) {
			sun.colour = BigLight.morning;
		} else {
			sun.colour = BigLight.early;
		}

		entities.sort(entitySorter);

	}

	public void render(Graphics2D g) {

		Camera.centerOnEntity(GameState.p);

		renderTile(g);

		for (Entity f : entities) {
			if (Camera.contains(f))
				f.render(g);
		}

		// sun.render(g);

		g.setColor(Color.BLACK);
		g.drawString("" + GameState.time, 8, 62);

	}

	public void init() {

	}

	public static final int MORNING = 1, EARLY_MORNING = 0, AFTERNOON = 2, EVENING = 3;

	public int timeIndex() {

		if (GameState.time >= 24 / 4 && GameState.time < 24 / 2) {
			return 1;
		} else if (GameState.time >= 24 / 2 && GameState.time < 24 / 2 + 24 / 4) {
			return 2;
		} else if (GameState.time >= 24 / 2 + 24 / 4 && GameState.time < 24) {
			return 3;
		} else {
			return 0;
		}

	}

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

}
