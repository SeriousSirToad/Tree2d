package kingery.ui;

import java.util.ArrayList;

import kingery.game.engine.Engine;

public class RenderOrder {

	public static ArrayList<GameComponent> components = new ArrayList<>();
	
	public static void render() {
		for (GameComponent b : components) {
			b.render();
		}
	}
	
	public static void add(GameComponent gc) {
		components.add(gc);
	}
	
	public static void remove(GameComponent gc) {
		components.remove(gc);
	}
	
}