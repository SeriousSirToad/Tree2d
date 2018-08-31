package kingery.game.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import kingery.game.engine.Engine;
import kingery.game.gfx.Assets;
import kingery.game.gfx.Camera;
import kingery.game.items.Item;

public class Inventory{
	private Engine e;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	private int InvX = 64, InvY = 64, InvWidth = 128, InvHeight = 128;
	
	public Inventory(Engine e) {
		this.e = e;
		inventoryItems = new ArrayList<Item>();
	}
	
	public void update() {
		if (e.input.space.isPressed()){
			active = true;
		}
		if (!active) {
			return;
		}
		System.out.println("Shit be working yo");
	}
	public void render(Graphics g) {
		if (!active) {
			return;
		}else {
		g.drawImage(Assets.INVENTORY, 0, 0, 64, 64, null);
		}
	}
	
	public void AddItem(Item item) {
		for(Item i: inventoryItems) {
			if(i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
			inventoryItems.add(item);
		}
	}
}
