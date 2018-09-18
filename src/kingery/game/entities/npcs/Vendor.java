package kingery.game.entities.npcs;

import kingery.game.engine.Engine;
import kingery.game.gfx.Assets;
import kingery.game.inventory.Inventory;
import kingery.game.islands.Island;

public class Vendor extends NPC{
	Inventory inventory; 
	
	public Vendor (int x, int y, String name, boolean inverted, Engine e, Island island) {
		super(x, y, "Want to trade?", name, true, Assets.getLumberjack(Lumberjack.LUMBERJACK_VENDOR), e, island);
		inventory = new Inventory(640, 0, 225, 225, e);
	}
	
	public void update() {
		if (interactZone.intersects(Engine.p.zoneCheck) && !Engine.p.moving) {
			if (e.input.E.isPressed()) {
				canShowBox = true;
			}


			if (canShowBox && !e.input.E.isPressed()) {
				int val = dialougeBox.drawQuestionBox(message, name);
				System.out.println(val);
				if(val == 0) {
					System.out.println(true);
				} else {
					System.out.println(false);
				}
				canShowBox = false;
			}

		}
	}

}


