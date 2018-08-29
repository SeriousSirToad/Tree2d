package kingery.game.gfx;

import java.awt.image.BufferedImage;
import java.util.*;

import kingery.game.islands.tiles.Tile;

public class Assets {
	
	//Entities
	public static BufferedImage PLAYER = SpriteSheet.getImage(0, 536, 32, 64);
	public static BufferedImage SHEEP_GENERIC = SpriteSheet.getImage(0, 504, 64, 32);
	public static BufferedImage NPC_LUMBERJACK_A = SpriteSheet.getImage(0, 440, 32, 64);
	
	//tiles
	
	public static BufferedImage randomGrass() {
		
		ArrayList<BufferedImage> images = new ArrayList<>();
		
		images.add(SpriteSheet.getImage(0, 0, Tile.textureWidth, Tile.textureWidth));
		images.add(SpriteSheet.getImage(8, 0, Tile.textureWidth, Tile.textureWidth));
		images.add(SpriteSheet.getImage(16, 0, Tile.textureWidth, Tile.textureWidth));
		Random rand = new Random();
		int in = rand.nextInt(images.size());
		
		return images.get(in);
		
	}
	
	//Items
	
}
