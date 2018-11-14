package kingery.game.entities.npcs;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import kingery.game.engine.Engine;
import kingery.game.entities.Mob;
import kingery.game.gfx.Camera;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;
import kingery.ui.NPCWindow;

public class NPC extends Mob {

	public Rectangle collider;
	public Rectangle interactZone;
	public String[] messages;
	public String name;
	protected boolean inverted = false;
	protected boolean vendor;
	protected NPCWindow dialogWindow;

	public NPC(int x, int y, String message, String name, boolean vendor, BufferedImage entityImage, Engine e,
			Island island) {
		super(x * Tile.width, y * Tile.width, name, entityImage, e, island);
		collider = new Rectangle(9, 43, 16, 22);
		interactZone = new Rectangle(x * Tile.width, y * Tile.width, width, height);
		messages = new String[1];
		messages[0] = message;
		this.entityImage = entityImage;
		this.name = name;
		this.vendor = vendor;

		dialogWindow = new NPCWindow(this);
	}

	public NPC(int x, int y, String[] messages, String name, BufferedImage entityImage, Engine e, Island island) {
		super(x * Tile.width, y * Tile.width, name, entityImage, e, island);
		collider = new Rectangle(9, 43, 16, 22);
		interactZone = new Rectangle(x * Tile.width, y * Tile.width, width, height);
		this.messages = messages;
		this.entityImage = entityImage;
		this.name = name;
		dialogWindow = new NPCWindow(this);
	}

	public boolean canShowBox = false;
	public int speechIndex = 0;

	@Override
	public void update() {

		if (interactZone.intersects(Engine.p.zoneCheck) && !Engine.p.moving) {
			if (e.input.E.isPressed()) {
				canShowBox = true;
			}
		}

		if (speechIndex == messages.length) {
			speechIndex = 0;
		}

	}

}
