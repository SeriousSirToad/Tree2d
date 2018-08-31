package kingery.game.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import kingery.game.engine.Engine;
import kingery.game.items.Item;

public class Inventory{
	private Engine e;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	public Inventory(Engine e) {
		this.e = e;
		inventoryItems = new ArrayList<Item>();
	}
	
	public void update() {
		if (e.input.space.isPressed()){
			active = !active;
		}
		if (!active)
			return;
		
		System.out.println("Shit be working yo");
	}
	public void render(Graphics g) {
		if (!active)
			return;
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
