package kingery.game.entities.npcs;

import kingery.game.engine.Engine;
import kingery.game.gfx.Assets;
import kingery.game.gfx.Camera;
import kingery.game.inventory.Inventory;
import kingery.game.islands.Island;

public class Vendor extends NPC{
	Inventory inventory; 
	
	public Vendor (int x, int y, String name, boolean inverted, Engine e, Island island) {
		super(x, y, "Want to trade?", name, true, Assets.getLumberjack(Lumberjack.LUMBERJACK_VENDOR), e, island);
		inventory = new Inventory(640, 0, 225, 225, e);
	}
	
	public void update() {
		if(!Camera.contains(this))
			return;
		if (interactZone.intersects(Engine.p.collider) && !Engine.p.moving) {
			if (e.input.E.isPressed()) {
				canShowBox = true;
			}


			if (canShowBox && !e.input.E.isPressed()) {
				canShowBox = false;
			}

		}
	}

}


