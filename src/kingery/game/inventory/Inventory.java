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
	public boolean active = false;
	private ArrayList<Item> inventoryItems;
	private int InvX = 0, InvY = 0, InvWidth = 225, InvHeight = 225;
	
	public Inventory(int InvX, int InvY, int InvWidth, int InvHeight, Engine e) {
		this.e = e;
		inventoryItems = new ArrayList<Item>();
	}
	
	public void update() {
		if (e.input.space.isPressed()){
			setActive(!isActive());
		}
		if (!isActive()) 
			return;
		
		System.out.println("Shit be working yo");
	}
	public void render(Graphics g) {
		if (!isActive()) 
			return;
		
		g.drawImage(Assets.INVENTORY, InvX, InvY, InvWidth, InvHeight, null);
		
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
