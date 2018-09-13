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
import kingery.game.gfx.TextBox;
import kingery.game.islands.Island;
import kingery.ui.GameWindow;

public class NPC extends Mob {

	public Rectangle collider;
	public Rectangle interactZone;
	public String[] messages;
	protected String message;
	protected TextBox dialougeBox;
	public String name;
	protected boolean inverted = false;
	protected boolean vendor;
	protected GameWindow dialogWindow;

	private Island l;

	public NPC(int x, int y, String message, String name, boolean vendor, BufferedImage entityImage, Engine e,
			Island island) {
		super(x, y, name, entityImage, e, island);
		collider = new Rectangle(9, 43, 16, 22);
		interactZone = new Rectangle(x, y, width, height);
		messages = new String[1];
		messages[0] = message;
		dialougeBox = new TextBox(e);
		this.entityImage = entityImage;
		this.name = name;
		this.vendor = vendor;

		dialogWindow = new GameWindow(this);
	}

	public NPC(int x, int y, String[] messages, String name, BufferedImage entityImage, Engine e, Island island) {
		super(x, y, name, entityImage, e, island);
		collider = new Rectangle(9, 43, 16, 22);
		interactZone = new Rectangle(x, y, width, height);
		this.messages = messages;
		dialougeBox = new TextBox(e);
		this.entityImage = entityImage;
		this.name = name;
		dialogWindow = new GameWindow(this);
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

	Font font = new Font(Font.MONOSPACED, Font.BOLD, 14);
	FontMetrics fm;

	public void render(Graphics g) {
		if (inverted)
			g.drawImage(entityImage, x - Camera.x() + width, y - Camera.y(), -width, height, null);
		else
			g.drawImage(entityImage, x - Camera.x(), y - Camera.y(), width, height, null);

		if (interactZone.intersects(Engine.p.zoneCheck) && !Engine.p.moving) {
			g.setFont(font);
			fm = g.getFontMetrics();
			int eWidth = fm.stringWidth("[E]");
			g.setColor(Color.WHITE);
			g.fillRect(Engine.p.x + e.p.width / 2 - eWidth / 2 - Camera.x(), Engine.p.y - Camera.y() - fm.getHeight(),
					eWidth, fm.getHeight());
			g.setColor(Color.black);
			g.drawString("[E]", Engine.p.x + Engine.p.width / 2 - fm.stringWidth("[E]") / 2 - Camera.x(),
					(int) (e.p.y - Camera.y() - Engine.p.height / 10));
		}

	}

}
