package kingery.game.gfx;

import java.awt.image.BufferedImage;
import java.util.*;

import kingery.game.islands.tiles.Tile;

public class Assets {
	
	//Entities
	public static BufferedImage PLAYER = SpriteSheet.getImage(0, 584, 8, 16);
	public static BufferedImage SHEEP_GENERIC = SpriteSheet.getImage(0, 504, 64, 32);
	public static BufferedImage NPC_LUMBERJACK_A = SpriteSheet.getImage(0, 568, 8, 16);
	public static BufferedImage NPC_LUMBERJACK_B = SpriteSheet.getImage(24, 568, 8, 16);
	public static BufferedImage NPC_LUMBERJACK_C = SpriteSheet.getImage(48, 568, 8, 16);
	public static BufferedImage NPC_LUMBERJACK_EVIL = SpriteSheet.getImage(72, 568, 8, 16);
	public static BufferedImage NPC_HERMIT = SpriteSheet.getImage(0, 552, 8, 8);
	//Building
	public static BufferedImage BLDG_SHOPPE_TEST = SpriteSheet.getImg(104, 0, 33, 49);
	
	//Items
	public static BufferedImage WOOD = SpriteSheet.getImage(0, 96,16, 7);
	public static BufferedImage MONEY = SpriteSheet.getImage(0, 108, 7, 3);
	
	//Inventory 
	public static BufferedImage INVENTORY = SpriteSheet.getImage(63, 0, 34, 34);
}
