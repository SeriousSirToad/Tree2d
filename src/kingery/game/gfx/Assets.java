package kingery.game.gfx;

import java.awt.image.BufferedImage;
import java.util.*;

import kingery.game.entities.npcs.Lumberjack;
import kingery.game.islands.tiles.Tile;

public class Assets {
	
	//Entities
	public static BufferedImage PLAYER = SpriteSheet.getImage(56, 584, 8, 16);
	public static BufferedImage SHEEP_GENERIC = SpriteSheet.getImage(0, 504, 64, 32);
	private static BufferedImage LUMBERJACK1 = SpriteSheet.getImage(0, 568, 8, 16);
	
	public static BufferedImage getLumberjack(int a) {
		BufferedImage LUMBERJACK = LUMBERJACK1;
		if(a == 0) {
			for(int y = 0; y < LUMBERJACK.getHeight(); y++) {
				for(int x = 0; x < LUMBERJACK.getWidth(); x++) {
					if(LUMBERJACK.getRGB(x, y) == 0xff7f7f7f) {
						LUMBERJACK.setRGB(x, y, Lumberjack.RED);
					}
					if(LUMBERJACK.getRGB(x, y) == 0xff3f3f3f) {
						LUMBERJACK.setRGB(x, y, Lumberjack.BLUE);
					}
				}
			}
			
			return LUMBERJACK;
			
		}
		
		return LUMBERJACK1;
	}
	
	public static BufferedImage NPC_HERMIT = SpriteSheet.getImage(0, 552, 8, 8);
	//Building
	public static BufferedImage BLDG_SHOPPE_TEST = SpriteSheet.getImg(128, 64, 80, 64);
	
	//Items
	public static BufferedImage WOOD = SpriteSheet.getImage(0, 96,16, 7);
	public static BufferedImage MONEY = SpriteSheet.getImage(0, 108, 7, 3);
	
	//Inventory 
	public static BufferedImage INVENTORY = SpriteSheet.getImage(63, 0, 34, 34);
}
