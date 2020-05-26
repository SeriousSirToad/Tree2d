package kingery.game.entities.npcs;

import kingery.game.engine.Engine;
import kingery.game.engine.GameState;
import kingery.game.gfx.Assets;
import kingery.game.inventory.Inventory;
import kingery.game.islands.Island;

public class Vendor extends NPC {
	Inventory inventory;

	public Vendor(int x, int y, String name, boolean inverted, Island island) {
		super(x, y, "Want to trade?", name, true, Assets.getLumberjack(Lumberjack.LUMBERJACK_VENDOR), island);
		inventory = new Inventory(640, 0, 225, 225);
	}

	public void tick() {
		if (!GameState.camera.contains(this))
			return;
		// if (interactZone.intersects(Engine.p.collider) && !Engine.p.moving) {
		if (Engine.input.E.isPressed()) {
			canShowBox = true;
		}

		if (canShowBox && !Engine.input.E.isPressed()) {
			canShowBox = false;
		}

		// }
	}

}
