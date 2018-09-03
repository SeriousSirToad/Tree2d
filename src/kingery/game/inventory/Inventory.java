package kingery.game.inventory;

import java.awt.Graphics;
import java.util.ArrayList;

import kingery.game.engine.Engine;
import kingery.game.gfx.Assets;
import kingery.game.items.Item;

public class Inventory {
	private Engine e;
	public boolean active = false;
	private ArrayList<Item> inventoryItems;
	private static final int InvWidth = 225, InvHeight = 225;

	public Inventory(int InvX, int InvY, int InvWidth, int InvHeight, Engine e) {
		this.e = e;
		inventoryItems = new ArrayList<Item>();
	}

	boolean canOpen = false;
	public void update() {
		
		if (e.input.space.isPressed()) {
			canOpen = true;
		}
		
		if (canOpen && !e.input.space.isPressed()) {
			active = !active;
			canOpen = false;
		}
	}

	public void render(Graphics g) {
		if (!isActive())
			return;

		g.drawImage(Assets.INVENTORY, Engine.WIDTH / 2 - (InvWidth / 2), Engine.HEIGHT / 2 - (InvHeight / 2), InvWidth,
				InvHeight, null);

	}

	public void AddItem(Item item) {
		for (Item i : inventoryItems) {
			if (i.getId() == item.getId()) {
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
