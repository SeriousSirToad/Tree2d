package kingery.game.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import kingery.game.engine.Engine;

public class ItemManager {
	private Engine e;
	private ArrayList<Item> items;
	
	public ItemManager(Engine e) {
		this.e = e;
		items = new ArrayList<Item>();
	}
	public void addItem(Item i) {
		items.add(i);
	}
	
	
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	public void update() {
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item i = it.next();
			i.update();
			if(i.getCount() == Item.PICKEDUP)
				it.remove();
		}
	}

	public void render(Graphics g) {
		for(Item i : items)
			i.render(g);
	}
	
}

