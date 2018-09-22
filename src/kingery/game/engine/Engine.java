package kingery.game.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import kingery.game.entities.Player;
import kingery.game.entities.buildings.Building;
import kingery.game.gfx.Assets;
import kingery.game.gfx.SpriteSheet;
import kingery.game.islands.Island;
import kingery.game.islands.tiles.Tile;
import kingery.game.menu.InGameMenu;
import kingery.game.menu.Menu;
import kingery.game.menu.Settings;
import kingery.ui.GameButton;
import kingery.ui.GameWindow;

public class Engine extends Canvas implements Runnable {

	private static final long serialVersionUID = -5198863677834462653L;

	public int tickCount;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public ArrayList<GameButton> buttons = new ArrayList<>();
	public static ArrayList<GameWindow> subwindows = new ArrayList<>();

	public InputHandler input = new InputHandler(this);
	public EntityHandler eHandle;

	public static Island island;

	public Menu menu = new Menu(this);
	InGameMenu inMenu;

	public static Player p;
	public int cameraX = 0;
	public int cameraY = 0;

	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

	public static int WIDTH = 256;
	public static int HEIGHT = 192;
	public static double SCALE = 2.5;
	private static final String NAME = "Tree Town alpha";

	BufferedImage backGround;
	public SpriteSheet spriteSheet;
	public boolean running = false;
	public JFrame frame = new JFrame();
	static final Dimension gameDimension = new Dimension((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE));

	public Engine() {

		WIDTH *= SCALE;
		HEIGHT *= SCALE;

		spriteSheet = new SpriteSheet("spriteSheet", "BuildingSpriteSheet.");
		frame.setTitle(NAME);
		frame.setSize(gameDimension);
		frame.setPreferredSize(gameDimension);
		frame.setMinimumSize(gameDimension);
		frame.setMaximumSize(gameDimension);
		frame.add(this, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.println(WIDTH + ", " + HEIGHT);

		inMenu = new InGameMenu(this);

		new Sound(this).start();
		initIslands();
		island = Island.Utopia;
		eHandle = new EntityHandler(this);
		p = eHandle.p;

	}

	public void initIslands() {

		Island.Utopia = new Island("res/Islands/Utopia.png", this);
		Island.Test = new Island("res/Islands/test.png", this);

	}

	boolean canEnterMenu = false;

	private void update() {

		if (menu.canStartGame() && !inMenu.inMenu) {

			if (!input.esc.isPressed()) {
				canEnterMenu = true;
			}
			if (canEnterMenu) {
				if (input.esc.isPressed()) {
					inMenu.inMenu = true;
					canEnterMenu = false;
				}
			}

			island.update();

		} else if (!menu.canStartGame() && !inMenu.inMenu) {

			menu.update();

		} else if (menu.canStartGame() && inMenu.inMenu) {

			inMenu.update();

		}

	}

	int frames = 0;

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60;
		double renderTime = 1000000000D / Settings.frameCap;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		double delta2 = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			delta2 += (now - lastTime) / renderTime;
			lastTime = now;
			boolean shouldRender = false;
			if (Settings.frameCap == 0) {
				shouldRender = true;
			}
			while (delta >= 1) {
				ticks++;
				tick();
				update();
				delta -= 1;
			}

			while (delta2 >= 1) {
				delta2 -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				render();
				frames++;
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");
				this.frames = frames;
				ticks = 0;
				frames = 0;
			}

		}
	}

	BufferStrategy bs;
	public static Graphics g;

	int xOffset = 0;
	int yOffset = 0;

	public void render() {

		// Creating graphics object
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}

		g = bs.getDrawGraphics();
		// General Rendering
		g.clearRect(0, 0, (WIDTH), (HEIGHT));
		if (menu.canStartGame()) {
			backGround(g);
			island.renderEntities(g);
			money(g);
			if (eHandle.p.inventory.active) {
				eHandle.p.inventory.render(g);
			}
		} else {
			g.setColor(Color.gray);
			g.fillRect(0, 0, (WIDTH), (HEIGHT));
			menu.renderTitle(g);
		}

		if (inMenu.inMenu) {
			inMenu.renderMenu(g);
		}

		for (GameWindow w : subwindows) {
			w.update(g);
		}

		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		g.drawString(frames + " fps", 10, 20);

		bs.show();
		g.dispose();
	}

	int moneyX = (int) (138 * SCALE), moneyY = (int) (2 * SCALE);
	int moneyW = (int) (116 * SCALE), moneyH = (int) (16 * SCALE);

	void money(Graphics g) {
		String playerMoneyAmnt = "$" + eHandle.p.money;
		g.fillRect(moneyX, moneyY, moneyW, moneyH);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		g.setColor(Color.BLUE);
		g.drawString(playerMoneyAmnt,
				moneyX + (moneyW / 6) - (g.getFontMetrics().stringWidth(playerMoneyAmnt) / 2),
				moneyY + (moneyH / 2) + (g.getFontMetrics().getHeight() / 4));
	}

	int bx = 0, by = 0;

	Color bg = new Color(0xFF87CEFA);

	void backGround(Graphics g) {

		if (island.time >= 6 && island.time < 12) {
			g.setColor(bg.darker());
		} else if (island.time >= 12 && island.time < 18) {
			g.setColor(bg);
		} else if (island.time >= 18 && island.time < 24) {
			g.setColor(bg.darker());
		} else {
			g.setColor(bg.darker().darker());
		}

		g.fillRect(0, 0, WIDTH, HEIGHT);

	}

	public Island getIsland() {
		return island;
	}

	void tick() {

		tickCount++;

	}

	public void start() {

		running = true;
		new Thread(this).start();

	}

	public static void main(String[] args) {
		new Engine().start();
	}

}