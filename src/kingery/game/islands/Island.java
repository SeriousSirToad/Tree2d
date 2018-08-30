package kingery.game.islands;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.imageio.ImageIO;

import kingery.game.engine.Engine;
import kingery.game.entities.Entity;
import kingery.game.gfx.Camera;
import kingery.game.islands.tiles.Tile;

public class Island {

	private Engine e;
	public BufferedImage levelImage;
	public ArrayList<Entity> entities = new ArrayList<>();
	boolean hasCycledA = false;
	boolean hasCycledB = false;
	private int[][] tiles;
	public int width;
	public int height;
	public String imagePath;
	private BufferedImage image;
	public Island rightI, leftI;
	//

	public static Island Utopia;
	public static Island Test;

	private Comparator<Entity> entitySorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if (a.y + a.height < b.y + b.height)
				return -1;
			return 1;
		}
	};

	public Island(String imagePath, Engine e) {
		this.e = e;
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadTiles() {
		int[] tileColours = this.image.getRGB(0, 0, width, height, null, 0,
				width);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileCheck: for (Tile t : Tile.tiles) {
					if (t != null
							&& t.getLevelColour() == tileColours[x + y * width]) {
						this.tiles[x][y] = t.getId();
						break tileCheck;
					}
				}
			}
		}
	}

	int xOffset, yOffset;

	public Tile getTile(int x, int y) {
		if (0 > x || x > width || 0 > y || y > height)
			return Tile.VOID;
		return Tile.tiles[tiles[x][y]];
	}

	public void renderTile(Graphics g) {

		int xMin = Math.max(0, Camera.x() / Tile.width);
		int xMax = Math
				.min(width, (Camera.x() + Engine.WIDTH) / Tile.width + 1);
		int yMin = Math.max(0, Camera.y() / Tile.width);
		int yMax = Math.min(height, (Camera.y() + Engine.HEIGHT) / Tile.width
				+ 1);

		for (int y = yMin; y < yMax; y++) {
			for (int x = xMin; x < xMax; x++) {
				getTile(x, y).renderTile(g, x * Tile.width - Camera.x(),
						y * Tile.width - Camera.y());
			}
		}

	}

	public void update() {

		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i).isMob()) {
				entities.get(i).update();
			}
		}
		
		if(rightI == null && leftI == null) {
			init();
		}
		
	}

	public void renderEntities(Graphics g) {

		Camera.centerOnEntity(e.eHandle.p);

		renderTile(g);

		for (Entity f : entities) {
			f.render(g);
		}
		entities.sort(entitySorter);

	}
	
	public void init() {
		
		if(this.equals(Test)) {
			rightI = Utopia;
		}
		if(this.equals(Utopia)) {
			leftI = Test;
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
