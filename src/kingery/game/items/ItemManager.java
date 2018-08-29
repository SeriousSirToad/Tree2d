package kingery.game.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import kingery.game.engine.Engine;

public class ItemManager {
	private Engine e;
	private ArrayList<Item> items = new ArrayList<Item>();

	public ItemManager(Engine e) {
		this.e = e;
	}

	public void addItem(Item i) {
		items.add(i);
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public void update() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).pickedUp)
				items.remove(i);
		}
	}

	public void render(Graphics g) {
		for (Item i : items)
			i.render(g);
	}

}
