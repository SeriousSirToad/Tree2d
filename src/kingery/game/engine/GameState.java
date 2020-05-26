package kingery.game.engine;

import java.util.Random;

import kingery.game.entities.Player;
import kingery.game.gfx.Camera;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;

public class GameState {

	static Engine engine;

	public static Player p;

	public Random r = new Random();

	public static Island currentLevel = Island.Start;

	public static int time = 0;
	
	public static Camera camera = new Camera(0, 0, currentLevel);

	public static void init() {

		currentLevel = Island.Start;
		p = new Player("Developer", Tile.width, Tile.width, Engine.input, currentLevel);
		camera = new Camera(0, 0, p);
		// 40 * Tile.width, 23 * Tile.width

	}

}
